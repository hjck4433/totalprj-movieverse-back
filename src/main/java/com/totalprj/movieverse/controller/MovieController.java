package com.totalprj.movieverse.controller;

import com.totalprj.movieverse.dto.MovieDto;
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

    // Kmdb Api 영화 조회
    @GetMapping("/apilist")
    public ResponseEntity<List<MovieDto>> getMovieList() {
        log.info("전체 영화정보 조회 진입");
        List<MovieDto> movieList = movieService.getMovieList();
        return ResponseEntity.ok(movieList);
    }


}
