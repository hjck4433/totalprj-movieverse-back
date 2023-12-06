package com.totalprj.movieverse.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name= "faq")
@Getter@Setter@ToString
@NoArgsConstructor
public class Faq {
    @Id
    @Column(name= "faq_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long faqId;
    private String faqQuestion;
    private String faqAnswer;

}
