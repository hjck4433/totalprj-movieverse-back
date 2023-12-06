package com.totalprj.movieverse.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter
@Setter
@ToString
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "board_title", nullable = false)
    private String title;
    @Column(name = "board_content", nullable = false)
    private String boardContent;
    private String image;
    @Column(name = "gather_type")
    private String gatherType;
    private int count;
    @Column(name = "registration_date")
    private LocalDateTime regdate;
    @PrePersist
    public void prepersist() {regdate = LocalDateTime.now();}

}
