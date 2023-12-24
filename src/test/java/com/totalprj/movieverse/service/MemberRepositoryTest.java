package com.totalprj.movieverse.service;

import com.totalprj.movieverse.entity.Member;
import com.totalprj.movieverse.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Transactional
@Slf4j
@RequiredArgsConstructor
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    public Member createMemberInfo() {
        Member member = new Member();
        member.setEmail("test@gmail.com");
        member.setPassword("Test1234!");
        member.setName("테스트");
        member.setAlias("햄스터");
        member.setPhone("010-1234-5678");
        member.setAddr("서울시 강남구 역삼동");
        member.setRegDate(LocalDateTime.now());
        return member;
    }

    @Test
    @DisplayName("회원 정보 저장 테스트")
    public void saveMemberTest(){
        Member member = createMemberInfo();
        Member testMember = memberRepository.save(member);

        em.flush();
        em.clear();
        System.out.println("testMember 결과 : " +testMember);
    }

    @Test
    @DisplayName("이메일 중복체크 테스트")
    public void isUniqeEmailTest() {

        Member member = createMemberInfo();
        memberRepository.save(member);
        boolean isUnique = memberRepository.existsByEmail("test1@gmail.com");

        em.flush();
        em.clear();
        System.out.println("isUnique 결과 : " +isUnique);

    }


    @Test
    @DisplayName("닉네임 중복 테스트")
    public void isUniqueAliasTest(){
        Member member = createMemberInfo();
        memberRepository.save(member);
        boolean isAliasUnique = memberRepository.existsByAlias("햄스터");

        em.flush();
        em.clear();
        System.out.println("isAliasUnique결과 : " +isAliasUnique);

    }

    @Test
    @DisplayName("전화번호 중복 테스트")
    public void isUniquePhoneTest(){
        Member member = createMemberInfo();
        memberRepository.save(member);
        boolean isPhoneUnique = memberRepository.existsByPhone("010-1234-5678");

        em.flush();
        em.clear();
        System.out.println("isPhoneUnique 결과 : " +isPhoneUnique);

    }

    @Test
    @DisplayName("내 정보 요청 테스트")
    public void memberDetailTest() {

        Member member = createMemberInfo();
        memberRepository.save(member);
        Optional<Member> detailMember = memberRepository.findById(1L);

        em.flush();
        em.clear();
        System.out.println(" memberDetailTest결과 : " +detailMember);

    }
}
