package com.totalprj.movieverse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDto {
    private Long commentId;
    private Long memberId;
    private Long boardId;
    private String content;
}
