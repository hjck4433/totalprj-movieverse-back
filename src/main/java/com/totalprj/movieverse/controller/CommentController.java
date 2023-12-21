package com.totalprj.movieverse.controller;

import com.totalprj.movieverse.dto.CommentReqDto;
import com.totalprj.movieverse.dto.CommentResDto;
import com.totalprj.movieverse.security.SecurityUtil;
import com.totalprj.movieverse.service.CommnetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommnetService commnetService;

    @PostMapping("/new")
    public ResponseEntity<Boolean> saveNewComment(@RequestBody CommentReqDto commentReqDto) {
        log.info("댓글 저장 진입");
        Long id = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.ok(commnetService.saveComment(commentReqDto, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List> commentList(Long id) {
        List<CommentResDto> list = commnetService.getCommentList(id);
        return ResponseEntity.ok(list);
    }
}
