package com.totalprj.movieverse.service;

import com.totalprj.movieverse.dto.MovieDto;
import com.totalprj.movieverse.dto.MovieResDto;
import com.totalprj.movieverse.dto.MovieSearchDto;
import com.totalprj.movieverse.entity.Movie;
import com.totalprj.movieverse.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
            if (!isExist && !Objects.equals(poster, "") && !Objects.equals(plot, "")) { // 영화 정보가 없으면 리스트에 추가

                checkList.add(movieDto);

            }
        }
        log.info("중복되지 않은 영화 수 : {} 건", checkList.size());

        return checkList;
    }


    // 영화 정보 저장
    public void saveMovieList(List<MovieDto> checkedList) {
        for (MovieDto movieDto : checkedList) {
            Movie movie = new Movie();
            movie.setTitle(movieDto.getTitle());
            movie.setPosters(movieDto.getPosters());
            movie.setTitleEng(movieDto.getTitleEng());
            movie.setReprlsDate(movieDto.getReprlsDate());
            movie.setGenre(movieDto.getGenre());
            movie.setNation(movieDto.getNation());
            movie.setRating(movieDto.getRating());
            movie.setRuntime(movieDto.getRuntime());
            movie.setScore(movieDto.getScore());
            movie.setDirectorNm(movieDto.getDirectorNm());
            movie.setActorNm(movieDto.getActorNm());
            movie.setPlotText(movieDto.getPlotText());
            movie.setStlls(movieDto.getStlls());
            saveMovie(movie);
        }
    }


    // 전체 DTO 변환
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
        movieDto.setScore(movie.getScore());
        movieDto.setDirectorNm(movie.getDirectorNm());
        movieDto.setActorNm(movie.getActorNm());
        movieDto.setPlotText(movie.getPlotText());
        movieDto.setStlls(movie.getStlls());
        return movieDto;
    }

    // 무비인포 DTO변환
    public MovieResDto convertToMovieInfo(Movie movie) {
        MovieResDto movieResDto = new MovieResDto();
        movieResDto.setId(movie.getId());
        movieResDto.setTitle(movie.getTitle());
        movieResDto.setPosters(movie.getPosters());
        movieResDto.setTitleEng(movie.getTitleEng());
        movieResDto.setReprlsDate(movie.getReprlsDate());
        movieResDto.setGenre(movie.getGenre());
        movieResDto.setRating(movie.getRating());
        movieResDto.setRuntime(movie.getRuntime());
        movieResDto.setScore(movie.getScore());
        movieResDto.setDirectorNm(movie.getDirectorNm());
        movieResDto.setActorNm(movie.getActorNm());
        movieResDto.setPlotText(movie.getPlotText());
        movieResDto.setStlls(movie.getStlls());
        return movieResDto;
    }

    // DB에서 영화 상세정보 가져오기
    public List<MovieResDto> getMovieDetail() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieResDto> movieDetail = new ArrayList<>();

        for (Movie movie : movies) {
            MovieResDto movieResDto = convertToMovieInfo(movie);
            movieDetail.add(movieResDto);
        }
        return movieDetail;
    }

    // 무비서치 DTO변환
    public MovieSearchDto convertToMovieSearch(Movie movie) {
        MovieSearchDto movieSearchDto = new MovieSearchDto();
        movieSearchDto.setId(movie.getId());
        movieSearchDto.setTitle(movie.getTitle());
        movieSearchDto.setPosters(movie.getPosters());
        movieSearchDto.setPlotText(movie.getPlotText());
        movieSearchDto.setScore(movie.getScore());
        return movieSearchDto;
    }

    // DB에서 영화정보 가져오기
    public List<MovieSearchDto> getMovieList() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieSearchDto> movieList = new ArrayList<>();

        for (Movie movie : movies) {
            MovieSearchDto searchDto = convertToMovieSearch(movie);
            movieList.add(searchDto);
        }
        return movieList;
    }

    // 무비리스트 페이지네이션
    public List<MovieSearchDto> getMovieList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Movie> movies = movieRepository.findAll(pageable).getContent();
        List<MovieSearchDto> movieList = new ArrayList<>();
        for (Movie movie : movies) {
            MovieSearchDto searchDto = convertToMovieSearch(movie);
            movieList.add(searchDto);
        }
        return movieList;

    }
    // 무비리스트 페이지 수 조회
    public int getMoviePage(Pageable pageable) {
        return movieRepository.findAll(pageable).getTotalPages();
    }
}
