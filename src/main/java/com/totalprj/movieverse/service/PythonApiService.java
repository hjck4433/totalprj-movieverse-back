package com.totalprj.movieverse.service;


import com.totalprj.movieverse.dto.MovieDto;
import com.totalprj.movieverse.entity.*;
import com.totalprj.movieverse.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
@EnableScheduling
public class PythonApiService {
    private final MovieService movieService;
    private final MovieRepository movieRepository;
    private final OttNetflixRepository netflixRepository;
    private final OttWatchaRepository watchaRepository;
    private final OttTvingRepository tvingRepository;
    private final BoxofficeRepository boxofficeRepository;

    @Bean
    @Scheduled(cron = "0 10 * * * *")
    public void startScheduler(){
        log.info("schedule start!");
        List<Map<String, List<Map<String, String>>>> response = fetchDataFromPythonServer();
        log.info("python response : {}", response);
        List<Map<String, List<Map<String, String>>>> ottList = ottDataList(response);
        log.info("movieDataList : {}", ottList);
        processList(ottList);
    }

    public List<Map<String, List<Map<String, String>>>> fetchDataFromPythonServer() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://127.0.0.1:5000/api/kmdblist";
        ResponseEntity<List<Map<String, List<Map<String, String>>>>> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, List<Map<String, String>>>>>() {});

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            log.error("Request failed with status code: {}", responseEntity.getStatusCodeValue());
            return null;
        }
    }
    // MovieInfo 만 저장하고 ott리스트 반환
    public List<Map<String, List<Map<String, String>>>> ottDataList(List<Map<String, List<Map<String, String>>>> movieList) {
        List<Map<String, Object>> res = new ArrayList<>();

        // 영화 정보만 뽑아내기
        Optional<Map<String, List<Map<String, String>>>> movieInfoMap = movieList.stream()
                .filter(map -> map.containsKey("movieInfo"))
                .findFirst();

        // 영화 정보가 있다면 DB 저장
        if (movieInfoMap.isPresent()) {
//            Map<String, Object> movie_info = new HashMap<>();
//            movie_info.put("movieInfo", movieInfoMap.get().get("movieInfo"));
//            res.add(movie_info);

            List<MovieDto> infoList = movieDtoList(movieInfoMap.get().get("movieInfo"));
            List<MovieDto> checkedList = movieService.checkExist(infoList);
            movieService.saveMovieList(checkedList);
        }

        // movieInfo를 제외한 나머지를 리스트로 묶어서 반환
        List<Map<String, List<Map<String, String>>>> ottMapList = movieList.stream()
                .filter(map -> !map.containsKey("movieInfo"))
                .collect(Collectors.toList());

        log.info("ottMapList : {}", ottMapList);
        return ottMapList;
    }
    // Otts 정보 분류
    public List<MovieDto> movieDtoList (List<Map<String,String>> movieInfo) {
        log.info("movieInfo : {}", movieInfo);
        List<MovieDto> movieDtoList = new ArrayList<>();
        for(Map<String,String> movie : movieInfo) {
            MovieDto movieDto = new MovieDto();
            log.info("title : {}", movie.get("title"));
            movieDto.setTitle(movie.get("title"));
            movieDto.setPosters(movie.get("posters"));
            movieDto.setTitleEng(movie.get("titleEng"));
            movieDto.setReprlsDate(movie.get("reprlsDate"));
            movieDto.setGenre(movie.get("genre"));
            movieDto.setNation(movie.get("nation"));
            movieDto.setRating(movie.get("rating"));
            movieDto.setRuntime(movie.get("runtime"));
            movieDto.setAudiAcc(movie.get("audiAcc"));
            movieDto.setDirectorNm(movie.get("directorNm"));
            movieDto.setActorNm(movie.get("actorNm"));
            movieDto.setPlotText(movie.get("plotText"));
            movieDtoList.add(movieDto);
        }

        return movieDtoList;
    }

    public void processList (List<Map<String, List<Map<String, String>>>> ottList){
        List<String> ls = Arrays.asList("box_office", "netflix", "watcha", "tving");

        for(String type : ls) {
            List<Map<String, String>> ottData = ottList.stream()
                    .filter(ott -> ott.containsKey(type))
                    .findFirst()
                    .map(ottMap -> ottMap.get(type))
                    .orElse(null);

            log.info("ottData : {}", ottData);
            if(ottData != null) {
                saveOtt(type, ottData);
            }else {
                log.warn("ottData 값이 없습니다.");
            }
        }
    }

    public void saveOtt(String type, List<Map<String, String>> ottData){
        switch(type) {
            case "box_office" :
                // box_office
                deleteAndSaveEntity(boxofficeRepository, Boxoffice.class ,ottData);
                break;
            case "netflix" :
                // netflix
                deleteAndSaveEntity(netflixRepository, OttNetflix.class, ottData);
                break;
            case "watcha" :
                deleteAndSaveEntity(watchaRepository, OttWatcha.class, ottData);
                break;
            case "tving":
                deleteAndSaveEntity(tvingRepository,OttTving.class, ottData);
                break;
            default:
                log.warn("ottData가 없습니다");
        }
    }
    private <T> void deleteAndSaveEntity(JpaRepository<T, ?>repository, Class<T> entityType, List<Map<String, String>> ottData){
        repository.deleteAll();
        for(Map<String, String> data : ottData) {
            T entity;
            // 새 entity 객체 생성
            try {
                entity = entityType.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Failed to create an instance of the entity.", e);
            }

            // 제목, 감독 정보를 기준으로 영화 검색
            String title = data.get("title");
            String director = data.get("director");
            log.info("title: {} director : {}", title, director);
            Movie movie = movieRepository.findByTitle(title)
                    .orElseThrow(() -> new RuntimeException(title+"영화가 존재하지 않습니다."));

            // 해당하는 entity에 set
            if (entity instanceof Boxoffice) {
                ((Boxoffice) entity).setMovie(movie);
            }else if(entity instanceof OttNetflix) {
                ((OttNetflix) entity).setMovie(movie);
            }else if(entity instanceof OttWatcha) {
                ((OttWatcha) entity).setMovie(movie);
            }else if(entity instanceof OttTving) {
                ((OttTving) entity).setMovie(movie);
            }

            // 저장
            repository.save(entity);

        }
    }


//// DB에 저장한 정보 비우기
//
//// 현재상영작 list(Map) 저장
//
//// ott(Netflix) list(Map) 저장
//
//// 현재상영작(watcha) list(Map) 저장
//
//// 현재상영작(tving) list(Map) 저장


//    // DTO 변환
//    private MovieDto convertEntityToDto(Movie movie) {
//        MovieDto movieDto = new MovieDto();
//        movieDto.setTitle(movie.getTitle());
//        movieDto.setPosters(movie.getPosters());
//        movieDto.setTitleEng(movie.getTitleEng());
//        movieDto.setReprlsDate(movie.getReprlsDate());
//        movieDto.setGenre(movie.getGenre());
//        movieDto.setNation(movie.getNation());
//        movieDto.setRating(movie.getRating());
//        movieDto.setRuntime(movie.getRuntime());
//        movieDto.setAudiAcc(movie.getAudiAcc());
//        movieDto.setDirectorNm(movie.getDirectorNm());
//        movieDto.setActorNm(movie.getActorNm());
//        movieDto.setPlotText(movie.getPlotText());
//        return movieDto;
//    }

}


