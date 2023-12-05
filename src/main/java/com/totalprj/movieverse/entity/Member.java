package com.totalprj.movieverse.entity;

import com.totalprj.movieverse.constant.Authority;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter @Setter @ToString
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String alias;
    private String phone;
    private String addr;
    private String image;

    @Column(name = "is_membership", columnDefinition = "TINYINT(1)")
    private boolean isMembership;
    @Column(name = "is_withdraw", columnDefinition = "TINYINT(1)")
    private boolean isWithdraw;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    // Jwt를 위한 빌더
    @Builder
    public Member(String email, String password, String name, String alias, String phone, String addr, String image, boolean isMembership, boolean isWithdraw, Authority authority) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.alias = alias;
        this.phone = phone;
        this.addr = addr;
        this.image = image;
        this.isMembership = isMembership;
        this.isWithdraw = isWithdraw;
        this.authority = authority;
    }
}
