package com.totalprj.movieverse.controller;

import com.totalprj.movieverse.security.SecurityUtil;
import com.totalprj.movieverse.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookMarkController {
    private final BookmarkService bookmarkService;

    @GetMapping("/isbookmark")
    public ResponseEntity<Boolean> getIsBookMark(@RequestParam Long movieId) {
        log.info("북마크 여부 확인 진입");
        Long memberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.ok(bookmarkService.isBookMarked(memberId, movieId));
    }

}
