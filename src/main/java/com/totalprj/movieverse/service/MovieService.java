package com.totalprj.movieverse.service;

import com.totalprj.movieverse.entity.Movie;
import com.totalprj.movieverse.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    // 영화 저장
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

//    // DTO 변환
//    private MovieDto convertEntityToDto(Movie movie) {
//        MovieDto movieDto = new MovieDto();
//        movieDto.setRank(movie.getMovieRank());
//        movieDto.setImage(movie.getImage());
//        movieDto.setTitle(movie.getTitle());
//        movieDto.setScore(movie.getScore());
//        movieDto.setRate(movie.getRate());
//        movieDto.setReservation(movie.getReservation());
//        movieDto.setDate(movie.getDate());
//        return movieDto;
//    }
//
//    Entity 변환
//    public void processAndSaveMovieData (List < Map < String, String >> movieList){
//        for (Map<String, String> data : movieList) {
//            Movie movie = new Movie();
//            movie.setMovieRank(data.get("rank"));
//            movie.setImage(data.get("image"));
//            movie.setTitle(data.get("title"));
//            movie.setScore(data.get("score"));
//            movie.setRate(data.get("eval_num"));
//            movie.setReservation(data.get("reservation"));
//            movie.setDate(data.get("open_date"));
//            this.saveMovie(movie);
//        }

//
//        // 영화 전체 조회
//        public List<MovieDto> getMovieList () {
//            List<Movie> movies = movieRepository.findAll();
//            List<MovieDto> movieDtos = new ArrayList<>();
//            for (Movie movie : movies) {
//                movieDtos.add(convertEntityToDto(movie));
//            }
//            return movieDtos;
//        }
//    }
}
