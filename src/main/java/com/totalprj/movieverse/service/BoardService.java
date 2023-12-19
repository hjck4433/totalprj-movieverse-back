package com.totalprj.movieverse.service;

import com.totalprj.movieverse.dto.BoardReqDto;
import com.totalprj.movieverse.entity.Board;
import com.totalprj.movieverse.entity.Category;
import com.totalprj.movieverse.entity.Member;
import com.totalprj.movieverse.repository.BoardRepository;
import com.totalprj.movieverse.repository.CategoryRepository;
import com.totalprj.movieverse.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    // 게시물 등록
    public boolean saveBoard(BoardReqDto boardDto, Long id) {
        try {
            Board board = new Board();
            Member member = memberRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            Category category = categoryRepository.findByCategoryName(boardDto.getCategoryName()).orElseThrow(
                    () -> new RuntimeException("해당 카테고리가 존재하지 않습니다.")
            );

            board.setMember(member);
            board.setCategory(category);
            board.setGatherType(boardDto.getGatherType());
            board.setTitle(boardDto.getTitle());
            board.setImage(boardDto.getImage());
            board.setBoardContent(boardDto.getBoardContent());

            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // 게시물 전체 조회

    // 게시물 상세 조회


    // 게시물 삭제
    public boolean deleteBoard(Long id) {
        try {
            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 게시글이 존재하지 않습니다.")
            );
            boardRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 게시물 검색

    // 게시글 페이징

    // 회원 이메일로 게시글 조회

    // 게시글 엔티티를 DTO로 변환

    // 페이지 수 조회
}
