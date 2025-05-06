package com.papasong.LibSchool.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    // 회원가입 화면
    @GetMapping("/register")
    public String register(){
        return "member/register";
    }

    // DB에 회원 Insert
    @PostMapping("/saveMember")
    public String saveMember(MemberDto dto){
        service.saveMember(dto);
        return "redirect:/index";
    }

    // 로그인 화면
    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

    // DB에서 검증 후 로그인
    @PostMapping("/processLogin")
    public String processLogin(MemberDto dto){
        boolean chk = service.processLogin(dto);

        if (chk){
            return "redirect:/index";
        } else {
            return "redirect:/login";
        }

    }
}
