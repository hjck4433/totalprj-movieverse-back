package com.totalprj.movieverse.controller;

import com.totalprj.movieverse.dto.MemberReqDto;
import com.totalprj.movieverse.dto.MemberResDto;
import com.totalprj.movieverse.service.AuthService;
import com.totalprj.movieverse.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MemberService memberService;
    @PostMapping("/isunique")
    public ResponseEntity<Boolean> isUnique (@RequestBody Map<String, String> dataMap) {
        int type = Integer.parseInt(dataMap.get("type"));
        return ResponseEntity.ok(authService.checkUnique(type, dataMap.get("data")));
    }

    @PostMapping("/join")
    public ResponseEntity<MemberResDto> join(@RequestBody MemberReqDto memberReqDto) {
        return ResponseEntity.ok(authService.join(memberReqDto));
    }
}
