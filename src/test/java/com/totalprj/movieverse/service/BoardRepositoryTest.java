package com.totalprj.movieverse.service;

import com.totalprj.movieverse.entity.Board;
import com.totalprj.movieverse.entity.Category;
import com.totalprj.movieverse.entity.Member;
import com.totalprj.movieverse.repository.BoardRepository;
import com.totalprj.movieverse.repository.CategoryRepository;
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
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@Slf4j
@RequiredArgsConstructor
@TestPropertySource(locations = "classpath:application-test.properties")
public class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    // 회원 정보 생성
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

    // 카테고리 정보 생성
    public Category createCategoryInfo(){
        Category category = new Category();
        category.setCategoryName("모임후기");
        return category;
    }

    @Test
    @DisplayName("게시글 카테고리 및 회원 정보 매핑 테스트")
    public void findCategoryAndMemberSaveTest(){
        Member member = createMemberInfo();
        memberRepository.save(member);
        Category category = createCategoryInfo();
        categoryRepository.save(category);

        Board board = new Board();
        board.setMember(member);
        board.setCategory(category);
        board.setGatherType("오프라인");
        board.setTitle("햄스터월드에서 햄스터를 만난 후기");
        board.setBoardContent("햄스터를 만나서 롤러코스터를 탔습니다!");
        board.setImage("햄스터이미지.jpg");
        board.setCount(0);
        boardRepository.save(board);

        em.flush();
        em.clear();

        Board savedBoard = boardRepository.findById(board.getId()).orElseThrow(EntityNotFoundException::new);

        System.out.println(savedBoard);
    }

}
