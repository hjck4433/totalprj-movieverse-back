package com.totalprj.movieverse.service;

import com.totalprj.movieverse.dto.MemberReqDto;
import com.totalprj.movieverse.dto.MemberResDto;
import com.totalprj.movieverse.entity.Member;
import com.totalprj.movieverse.jwt.TokenProvider;
import com.totalprj.movieverse.repository.MemberRepository;
import com.totalprj.movieverse.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder; // 인증 담당 클래스
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public Boolean checkUnique(int type, String info) {
        boolean isUnique;
        switch (type) {
            case 0 :
                isUnique = memberRepository.existsByEmail(info);
                break;
            case 1 :
                isUnique = memberRepository.existsByAlias(info);
                break;
            case 2:
                isUnique = memberRepository.existsByPhone(info);
                break;
            default: isUnique = true; log.info("중복체크 구분이 잘 못 되었습니다");
        }
        return isUnique;
    }

    public MemberResDto join(MemberReqDto memberReqDto) {
        if(memberRepository.existsByEmail(memberReqDto.getEmail())){
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        Member member = memberReqDto.toEntity(passwordEncoder);
        return MemberResDto.of(memberRepository.save(member));
    }

}
