package com.totalprj.movieverse.controller;

import com.totalprj.movieverse.dto.FaqDto;
import com.totalprj.movieverse.service.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {
    private final FaqService faqService;

    @PostMapping("/new")
    public ResponseEntity<Boolean> createFaq(@RequestBody FaqDto faqDto){
        log.info("FAQ 저장하기: {}", faqDto);
        return ResponseEntity.ok(faqService.createFaq(faqDto)); // 저장된 faq dto 반환

    }
}
