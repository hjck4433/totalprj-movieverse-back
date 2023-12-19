package com.totalprj.movieverse.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {
    private Long boardId;
    private String categoryName;
    private String title;
    private String content;
    private String image;
    private String gatherType;
}
