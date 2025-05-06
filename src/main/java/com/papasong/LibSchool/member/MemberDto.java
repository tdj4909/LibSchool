package com.papasong.LibSchool.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberDto {

    private String username;
    private String password;
    private String email;
    private String name;

}
