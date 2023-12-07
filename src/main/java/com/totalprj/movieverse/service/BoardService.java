package com.totalprj.movieverse.service;

import com.totalprj.movieverse.dto.BoardDto;
import com.totalprj.movieverse.entity.Board;
import com.totalprj.movieverse.entity.Category;
import com.totalprj.movieverse.entity.Member;
import com.totalprj.movieverse.repository.BoardRepository;
import com.totalprj.movieverse.repository.CategoryRepository;
import com.totalprj.movieverse.repository.MemberRepository;
import com.totalprj.movieverse.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    // 게시물 등록
    public boolean saveBoard(BoardDto boardDto) {
        try {
            Board board = new Board();
            Member member = memberRepository.findByEmail(boardDto.getEmail()).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            Category category = categoryRepository.findById(boardDto.getCategoryId()).orElseThrow(
                    () -> new RuntimeException("해당 카테고리가 존재하지 않습니다.")
            );

            board.setTitle(boardDto.getTitle());
            board.setCategory(category);
            board.setBoardContent(boardDto.getContent());
            board.setImage(boardDto.getImage());
            board.setMember(member);
            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 게시물 전체 조회
    public List<BoardDto> BoardList() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        for (Board board : boards) {
            boardDtos.add(converEntityToDto(board));
        }
        return boardDtos;
    }
    // 게시물 상세 조회
    public BoardDto BoardDetail(BoardDto boardDto) {
        Long id = SecurityUtil.getCurrentMemberId();
        Member member = memberRepository.findById(id).orElseThrow();
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("해당 게시글이 존재하지 않습니다.")
        );
        return converEntityToDto(board);
    }
    // 게시물 수정
    public boolean modifyBoard(Long id, BoardDto boardDto) {
        try {
//            Member member = memberRepository.findByEmail(boardDto.getEmail()).orElseThrow(
//                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
//            );
            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 게시글이 존재하지 않습니다.")
            );
            board.setTitle(boardDto.getTitle());
            board.setImage(boardDto.getImage());
            board.setBoardContent(boardDto.getContent());
            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
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
    private BoardDto converEntityToDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setCategoryId(board.getCategory().getId());
        boardDto.setContent(board.getBoardContent());
        boardDto.setImage(board.getImage());
        boardDto.setRegdate(board.getRegdate());
        return boardDto;
    }
    // 페이지 수 조회
}
