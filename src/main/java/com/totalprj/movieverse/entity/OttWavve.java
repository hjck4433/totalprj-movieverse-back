package com.totalprj.movieverse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="ott_wavve")
@Getter
@Setter
@NoArgsConstructor
public class OttWavve {
    @Id
    @Column(name="wavve_id") // 북마크 ID
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 영화 ID 외래키
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name="movie_rank") // 영화 순위
    private int movieRank;

}
