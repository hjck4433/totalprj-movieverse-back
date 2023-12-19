package com.totalprj.movieverse.service;

import com.totalprj.movieverse.dto.MemberReqDto;
import com.totalprj.movieverse.dto.MemberResDto;
import com.totalprj.movieverse.entity.Kakao;
import com.totalprj.movieverse.entity.Member;
import com.totalprj.movieverse.repository.KakaoRepository;
import com.totalprj.movieverse.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final KakaoRepository kakaoRepository;

    // 회원 상세 조회
    public MemberResDto getMemberDetail(Long id){
        Member member = memberRepository.findById(id).orElseThrow(()-> new RuntimeException("해당 회원이 존재하지 않습니다."));
        return convertEntityToDto(member);
    }

    // 비밀번호 일치 체크
    public boolean isPassword(String password, Long id){
        log.info("password : {}" , password);
        Member member = memberRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));
        boolean isPw = passwordEncoder.matches(password, member.getPassword());
        log.info("isPw : {}", isPw);
        return isPw;
    }

    // 회원 정보 수정
    public boolean modifyMember(MemberReqDto memberReqDto) {
        log.info("password : {}", memberReqDto.getPassword());
        try {
            Member member = memberRepository.findByEmail(memberReqDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));
            // 카카오 회원이 경우 비밀번호 수정 X
            log.info("카카오 회원? : {}", member.isKakao());
            if(!member.isKakao() && !memberReqDto.getPassword().isEmpty()) {
                member.setPassword(passwordEncoder.encode(memberReqDto.getPassword()));
            }
            member.setAlias(memberReqDto.getAlias());
            member.setPhone(memberReqDto.getPhone());
            member.setAddr(memberReqDto.getAddr());
            member.setImage(memberReqDto.getImage());
            member.setRegDate(LocalDateTime.now());
            memberRepository.save(member);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 회원 탈퇴
    public boolean withdrawMember(Long id) {
        try {
            Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));
            member.setWithdraw(true);
            memberRepository.save(member);
            return true;
        }catch (Exception e){
            log.info("회원탈퇴 처리 중 오류 발생");
            return false;
        }
    }

    // 멤버십 여부 업데이트
    public boolean membershipSave(Long id) {
        try{
            Member member = memberRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));
            member.setMembership(true);
            memberRepository.save(member);
            return true;
        }catch (Exception e){
            log.error("멤버십 가입 처리 중 오류 발생");
            return false;
        }
    }

    // 멤버십 여부 가져오기
    public boolean isMembership(Long id) {
        try {
            Member member = memberRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));
            boolean isKiki = member.isMembership();
            log.info("isKiKi ? : {}", isKiki);
            return isKiki;
        }catch (Exception e){
            log.error("멥버십 정보 가져 오는 중 오류 발생");
            return false;
        }
    }


    // 회원 엔티티를 회원 DTO로 변환
    private MemberResDto convertEntityToDto(Member member){
        MemberResDto memberResDto = new MemberResDto();
        memberResDto.setEmail(member.getEmail());
        memberResDto.setName(member.getName());
        memberResDto.setAlias(member.getAlias());
        memberResDto.setPhone(member.getPhone());
        memberResDto.setAddr(member.getAddr());
        memberResDto.setImage(member.getImage());
        memberResDto.setIsMembership(member.isMembership());
        memberResDto.setIsKakao(member.isKakao());
        return memberResDto;
    }


}


