package com.totalprj.movieverse.controller;


import com.totalprj.movieverse.dto.MemberReqDto;
import com.totalprj.movieverse.dto.MemberResDto;
import com.totalprj.movieverse.security.SecurityUtil;
import com.totalprj.movieverse.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    // 회원 상세 조회
    @GetMapping("/detail")
    public ResponseEntity<MemberResDto> memberDetail(){
        Long id = SecurityUtil.getCurrentMemberId();
        log.info("id : {} ", id);
        MemberResDto memberResDto = memberService.getMemberDetail(id);
        return ResponseEntity.ok(memberResDto);
    }

    //회원 정보 수정
    @PostMapping("/update")
    public ResponseEntity<Boolean> updateMember(@RequestBody MemberReqDto memberReqDto){
        log.info("memberReqDto : {}", memberReqDto);
        return ResponseEntity.ok(memberService.modifyMember(memberReqDto));
    }

    //비밀번호 체크
    @PostMapping("/ispassword")
    public ResponseEntity<Boolean> checkPw(@RequestBody Map<String, String> data){
        Long id = SecurityUtil.getCurrentMemberId();
        String password = data.get("password");
        log.info("password : {}", password);
        return ResponseEntity.ok(memberService.isPassword(password, id));
    }

    // 멤버십 여부 업데이트
    @PostMapping("/membership")
    public ResponseEntity<Boolean> updateMembership(@RequestBody Map<String, Boolean> data){
        Long id = SecurityUtil.getCurrentMemberId();// 토근에서 id 정보를 가져옴
        log.info("id : {}", id);
        return ResponseEntity.ok(memberService.membershipSave(id));
    }



    // 회원 탈퇴
    @PostMapping("/withdraw")
    public ResponseEntity<Boolean> withdrawMember(){
        Long id = SecurityUtil.getCurrentMemberId();
        log.info("id : {}", id);
        return ResponseEntity.ok(memberService.withdrawMember(id));
    }


}