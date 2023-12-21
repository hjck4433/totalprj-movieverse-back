package com.totalprj.movieverse.service;

import com.totalprj.movieverse.dto.CommentReqDto;
import com.totalprj.movieverse.dto.CommentResDto;
import com.totalprj.movieverse.entity.Board;
import com.totalprj.movieverse.entity.Comment;
import com.totalprj.movieverse.entity.Member;
import com.totalprj.movieverse.repository.BoardRepository;
import com.totalprj.movieverse.repository.CommentRepository;
import com.totalprj.movieverse.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommnetService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    // 댓글 등록
    public boolean saveComment (CommentReqDto commentReqDto, Long id) {
        try  {
            Comment comment = new Comment();
            Member member = memberRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            Board board = boardRepository.findById(commentReqDto.getBoardId()).orElseThrow(
                    () -> new RuntimeException("해당 게시글이 없습니다.")
            );
            comment.setMember(member);
            comment.setBoard(board);
            comment.setCommentContent(commentReqDto.getCommentContent());
            comment.setCommentRegdate(LocalDateTime.now());
            commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 댓글 전체 목록 조회
    public List<CommentResDto> getCommentList (Long id) {
        List<Comment> comments = commentRepository.findAll();
        List<CommentResDto> commentResDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentResDtos.add(convertEntityToDto(comment));
        }
        return commentResDtos;
    }

    // 댓글 엔티티를 Dto로 변환
    public CommentResDto convertEntityToDto (Comment comment) {
        CommentResDto commentResDto = new CommentResDto();
        commentResDto.setBoardId(comment.getBoard().getId());
        commentResDto.setMemberAlias(comment.getMember().getAlias());
        commentResDto.setMemberImage(comment.getMember().getImage());
        commentResDto.setCommentContent(comment.getCommentContent());
        commentResDto.setCommentId(comment.getId());
        commentResDto.setCommentRegDate(comment.getCommentRegdate());
        return commentResDto;
    }
}
