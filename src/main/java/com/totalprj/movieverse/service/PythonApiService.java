//package com.totalprj.movieverse.service;
//
//
//import com.totalprj.movieverse.dto.MovieDto;
//import com.totalprj.movieverse.entity.Movie;
//import com.totalprj.movieverse.repository.MovieRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.lang.reflect.Array;
//import java.util.*;
//import java.util.stream.Collectors;
//
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//@EnableScheduling
//public class PythonApiService {
//    private final MovieRepository movieRepository;
//    @Bean
//    @Scheduled(cron = "0 1 * * * *")
//    public void startScheduler(){
//        log.info("schedule start!");
//        List<Map<String, List<Map<String, String>>>> response = fetchDataFromPythonServer();
//        log.info("python response : {}", response);
//    }
//
//    public List<Map<String, List<Map<String, String>>>> fetchDataFromPythonServer() {
//        RestTemplate restTemplate = new RestTemplate();
//        String apiUrl = "http://127.0.0.1:5000/api/kmdblist";
//        ResponseEntity<List<Map<String, List<Map<String, String>>>>> responseEntity = restTemplate.exchange(
//                apiUrl,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Map<String, List<Map<String, String>>>>>() {});
//
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            return responseEntity.getBody();
//        } else {
//            log.error("Request failed with status code: {}", responseEntity.getStatusCodeValue());
//            return null;
//        }
//    }
//    // MovieInfo 정보 분류
//    public List<Map<String, Object>> movieInfoData(List<Map<String, List<Map<String, String>>>> movieList) {
//        List<Map<String, Object>> res = new ArrayList<>();
//
//        Optional<Map<String, List<Map<String, String>>>> movieInfoMap = movieList.stream()
//                .filter(map -> map.containsKey("movieInfo"))
//                .findFirst();
//
//        if (movieInfoMap.isPresent()) {
//            Map<String, Object> movie_info = new HashMap<>();
//            movie_info.put("movieInfo", movieInfoMap.get().get("movieInfo"));
//            res.add(movie_info);
//        }
//
//        List<Map<String, List<Map<String, String>>>> typesMapList = movieList.stream()
//                .filter(map -> !map.containsKey("movieInfo"))
//                .collect(Collectors.toList());
//
//        Map<String, Object> typesMap = new HashMap<>();
//        typesMap.put("types", typesMapList);
//
//        res.add(typesMap);
//
//        return res;
//    }
//    // Otts 정보 분류
//    public List<MovieDto> movieDtoList (List<Map<String,String>> movieInfo) {
//        List<MovieDto> movieDtoList = new ArrayList<>();
//        for(Map<String,String> movie : movieInfo) {
//            MovieDto movieDto = new MovieDto();
//            movieDto.setTitle(movie.get("title"));
//            movieDto.setPosters(movie.get("posters"));
//            movieDto.setTitleEng(movie.get("titleEng"));
//            movieDto.setReprlsDate(movie.get("reprlsDate"));
//            movieDto.setGenre(movie.get("genre"));
//            movieDto.setNation(movie.get("nation"));
//            movieDto.setRating(movie.get("rating"));
//            movieDto.setRuntime(movie.get("runtime"));
//            movieDto.setAudiAcc(movie.get("audiAcc"));
//            movieDto.setDirectorNm(movie.get("directorNm"));
//            movieDto.setActorNm(movie.get("actorNm"));
//            movieDto.setPlotText(movie.get("plotText"));
//        }
//
//        return movieDtoList;
//    }
//
//
////// DB에 저장한 정보 비우기
////
////// 현재상영작 list(Map) 저장
////
////// ott(Netflix) list(Map) 저장
////
////// 현재상영작(watcha) list(Map) 저장
////
////// 현재상영작(tving) list(Map) 저장
//
//
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
//
//}
//
//
