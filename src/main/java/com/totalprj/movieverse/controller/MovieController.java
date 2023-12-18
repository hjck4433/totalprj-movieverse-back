package com.totalprj.movieverse.controller;

import com.totalprj.movieverse.dto.MovieDto;
import com.totalprj.movieverse.dto.MovieSearchDto;
import com.totalprj.movieverse.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // 무비서치 페이지네이션
    @GetMapping("/movielist/page")
    public ResponseEntity<List<MovieSearchDto>> getMovieList(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "4") int size) {
        List<MovieSearchDto> movieList = movieService.getMovieList(page, size);
        log.info("movieList : {}", movieList);
        return ResponseEntity.ok(movieList);
    }

    // 무비서치 페이지 수 조회
    @GetMapping("/movielist/count")
    public ResponseEntity<Integer> movieCount(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "4") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        int pageCnt = movieService.getMoviePage(pageRequest);
        return ResponseEntity.ok(pageCnt);
    }
}
