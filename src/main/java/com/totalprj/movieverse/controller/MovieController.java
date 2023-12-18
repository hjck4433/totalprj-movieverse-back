package com.totalprj.movieverse.controller;

import com.totalprj.movieverse.dto.MovieDto;
import com.totalprj.movieverse.dto.MovieSearchDto;
import com.totalprj.movieverse.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    // 전체 영화 조회
    @GetMapping("/movielist")
    public ResponseEntity<List<MovieSearchDto>> getMovieList() {
        log.info("전체 영화정보 조회 진입");
        List<MovieSearchDto> movieList = movieService.getMovieList();
        return ResponseEntity.ok(movieList);
    }


}
