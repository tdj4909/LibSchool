package com.papasong.LibSchool.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void saveMember(MemberDto dto){
        Member member = new Member();
        member.setUsername(dto.getUsername());
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        member.setEmail(dto.getEmail());
        member.setName(dto.getName());
        repository.save(member);
    }

    public boolean processLogin(MemberDto dto){
        Member member = repository.findByUsername(dto.getUsername());
        System.out.println(member);
        if (member == null){
            System.out.println("아이디가 존재하지 않습니다");
            return false;
        } else {
            if (member.getUsername().equals(dto.getUsername()) &&
                passwordEncoder.matches(dto.getPassword(), member.getPassword())){
                System.out.println("로그인 되었습니다.");
                return true;
            } else {
                System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
                return false;
            }
        }

    }

}
