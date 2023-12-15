package com.totalprj.movieverse.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieSearchDto {
    private String title;
    private String posters;
}
