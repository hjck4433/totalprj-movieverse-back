package com.totalprj.movieverse.controller;

import com.totalprj.movieverse.dto.BoardReqDto;
import com.totalprj.movieverse.dto.BoardResDto;
import com.totalprj.movieverse.security.SecurityUtil;
import com.totalprj.movieverse.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 게시글 전체 조회
    @GetMapping("/{id}")
    public ResponseEntity<List> boardList(Long id) {
//        log.info("게시판 조회 id : {}", id);
        List<BoardResDto> list = boardService.getBoardList(id);
        return ResponseEntity.ok(list);
    }

    // 게시글 상세 조회
    // 질문 ! boardList는 Back주소인데 리액트랑 안맞아도 상관없는거 아닌가요?
    @GetMapping("/post/{postId}")
    public ResponseEntity<BoardResDto> boardDetail(@PathVariable Long postId) {
        log.info("게시판 게시글정보 조회 postId : ", postId);
        BoardResDto boardResDto = boardService.getBoardDetail(postId);
        return ResponseEntity.ok(boardResDto);
    }

    // 게시글 최신순 페이지네이션
//    @GetMapping()

}
