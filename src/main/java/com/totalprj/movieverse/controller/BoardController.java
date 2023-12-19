package com.totalprj.movieverse.controller;

import com.totalprj.movieverse.dto.BoardReqDto;
import com.totalprj.movieverse.security.SecurityUtil;
import com.totalprj.movieverse.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/new")
    public ResponseEntity<Boolean> saveNewBoard(@RequestBody BoardReqDto boardReqDto){
        log.info("새 게시글 저장 진입");
        Long id = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.ok(boardService.saveBoard(boardReqDto, id));
    }
}
