package com.totalprj.movieverse.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="chating")
@ToString
@NoArgsConstructor
public class Chating {
    @Id // 이 Id는 PRIMARY KEY로 들어가는것을 의미
    @Column(name="chating_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="room_name")
    private String roomName;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
