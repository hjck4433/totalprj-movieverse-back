package com.totalprj.movieverse.dto;

import com.totalprj.movieverse.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResDto {
    private String email;
    private String name;
    private String alias;
    private String phone;
    private String addr;
    private String image;
    private Boolean isMembership;

    //Member -> MemberResDto
    public static MemberResDto of(Member member) {
        return MemberResDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .alias(member.getAlias())
                .phone(member.getPhone())
                .addr(member.getAddr())
                .image(member.getImage())
                .isMembership(member.isMembership())
                .build();
    }
}
