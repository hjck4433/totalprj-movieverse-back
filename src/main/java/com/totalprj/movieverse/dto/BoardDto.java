package com.totalprj.movieverse.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {
    private Long boardId;
    private Long categoryId;
    private String email;
    private String title;
    private String content;
    private String image;
    private String gatherType;
    private int count;
    private LocalDateTime regdate;
}
