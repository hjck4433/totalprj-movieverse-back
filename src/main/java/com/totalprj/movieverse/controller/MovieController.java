package com.totalprj.movieverse.controller;

import com.totalprj.movieverse.dto.MovieDto;
import com.totalprj.movieverse.security.SecurityUtil;
import com.totalprj.movieverse.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    // KMDB API 영화 조회
//    @GetMapping("/api/apilist")
//    public ResponseEntity<MovieDto> movieDetail(){
//        Long id = SecurityUtil.getCurrentMemberId();
//        log.info("title : {} ", title);
//        MovieDto MovieDto = MovieService.(title);
//        return ResponseEntity.ok(movie);
//    }
}
