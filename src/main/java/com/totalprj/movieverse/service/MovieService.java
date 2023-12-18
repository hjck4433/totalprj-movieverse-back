package com.totalprj.movieverse.service;

import com.totalprj.movieverse.dto.MovieDto;
import com.totalprj.movieverse.entity.Movie;
import com.totalprj.movieverse.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import com.totalprj.movieverse.service.KmdbApiService;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    // 영화 저장
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    // 타이틀, 감독만 뽑아낸 List<Map<String, String>>
    public List<MovieDto> checkExist(List<MovieDto> movieList) {
        log.info("movieLsit for check : {}", movieList);
        List<MovieDto> checkList = new ArrayList<>();

        for (MovieDto movieDto : movieList) {
                String title = movieDto.getTitle();
                String director = movieDto.getDirectorNm();
                String poster = movieDto.getPosters();
                String plot = movieDto.getPlotText();
                log.info("title : {} , director : {}", title, director);
                boolean isExist = movieRepository.existsByTitleAndDirectorNm(title, director);
            if(!isExist && !Objects.equals(poster, "") && !Objects.equals(plot, "")) { // 영화 정보가 없으면 리스트에 추가

                checkList.add(movieDto);

            }
        }
        log.info("중복되지 않은 영화 수 : {} 건", checkList.size());

        return checkList;
    }



    // 영화 정보 저장
    public void saveMovieList(List<MovieDto> checkedList){
        for(MovieDto movieDto : checkedList) {
            Movie movie = new Movie();
            movie.setTitle(movieDto.getTitle());
            movie.setPosters(movieDto.getPosters());
            movie.setTitleEng(movieDto.getTitleEng());
            movie.setReprlsDate(movieDto.getReprlsDate());
            movie.setGenre(movieDto.getGenre());
            movie.setNation(movieDto.getNation());
            movie.setRating(movieDto.getRating());
            movie.setRuntime(movieDto.getRuntime());
            movie.setAudiAcc(movieDto.getAudiAcc());
            movie.setDirectorNm(movieDto.getDirectorNm());
            movie.setActorNm(movieDto.getActorNm());
            movie.setPlotText(movieDto.getPlotText());
            movie.setStlls(movieDto.getStlls());
            saveMovie(movie);
        }
    }


    // DTO 변환
    private MovieDto convertEntityToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(movie.getTitle());
        movieDto.setPosters(movie.getPosters());
        movieDto.setTitleEng(movie.getTitleEng());
        movieDto.setReprlsDate(movie.getReprlsDate());
        movieDto.setGenre(movie.getGenre());
        movieDto.setNation(movie.getNation());
        movieDto.setRating(movie.getRating());
        movieDto.setRuntime(movie.getRuntime());
        movieDto.setAudiAcc(movie.getAudiAcc());
        movieDto.setDirectorNm(movie.getDirectorNm());
        movieDto.setActorNm(movie.getActorNm());
        movieDto.setPlotText(movie.getPlotText());
        movieDto.setStlls(movie.getStlls());
        return movieDto;
    }

    // KMDB 영화정보 가져오기
    public List<MovieDto> getMovieList() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDto> movieList = new ArrayList<>();

        for (Movie movie : movies) {
            MovieDto movieDto = convertEntityToDto(movie);
            movieList.add(movieDto);
        }

        return movieList;
    }





}
