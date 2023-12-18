package com.totalprj.movieverse.controller;

import com.totalprj.movieverse.dto.MovieSearchDto;
import com.totalprj.movieverse.service.OttBoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/ottbox")
@RequiredArgsConstructor
public class OttBoxController {
    private final OttBoxService ottBoxService;

    //boxOffice 영화 리스트
    @GetMapping("/boxoffice")
    public ResponseEntity<List<MovieSearchDto>> getBoxOfficeList(){
        log.info("boxoffice 리스트 조회 진입");
        List<MovieSearchDto> movieList = ottBoxService.boxOfficeList();
        log.info("boxoffice list : {}", movieList);
        return ResponseEntity.ok(movieList);
    }

}
