package com.totalprj.movieverse.service;

import com.totalprj.movieverse.dto.MemberResDto;
import com.totalprj.movieverse.entity.Member;
import com.totalprj.movieverse.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 상세 조회
    public MemberResDto getMemberDetail(Long id){
        Member member = memberRepository.findById(id).orElseThrow(()-> new RuntimeException("해당 회원이 존재하지 않습니다."));
        return convertEntityToDto(member);
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
        return memberResDto;
    }


}


