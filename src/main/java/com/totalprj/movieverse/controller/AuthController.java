package com.totalprj.movieverse.controller;

import com.totalprj.movieverse.dto.AccessTokenDto;
import com.totalprj.movieverse.dto.MemberReqDto;
import com.totalprj.movieverse.dto.MemberResDto;
import com.totalprj.movieverse.dto.TokenDto;
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

    //중복체크
    @PostMapping("/isunique")
    public ResponseEntity<Boolean> isUnique (@RequestBody Map<String, String> dataMap) {
        int type = Integer.parseInt(dataMap.get("type"));
        return ResponseEntity.ok(authService.checkUnique(type, dataMap.get("data")));
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<MemberResDto> join(@RequestBody MemberReqDto memberReqDto) {
        log.info("memberReqDto : {}", memberReqDto);
        return ResponseEntity.ok(authService.join(memberReqDto));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberReqDto memberReqDto) {
        return ResponseEntity.ok(authService.login(memberReqDto));
    }

    // 리프레시 토큰으로 새 액세스 토큰 발급
    @PostMapping("/refresh")
    public ResponseEntity<AccessTokenDto> newToken(@RequestBody String refreshToken) {
        log.info("refreshToken: {}", refreshToken);
        return ResponseEntity.ok(authService.refreshAccessToken(refreshToken));
    }

}
