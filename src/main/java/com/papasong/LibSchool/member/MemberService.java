package com.papasong.LibSchool.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<Member> optionalMember = repository.findByUsername(dto.getUsername());

        if (optionalMember.isEmpty()){
            System.out.println("아이디가 존재하지 않습니다");
        } else {
            Member member = optionalMember.get();

            if (passwordEncoder.matches(dto.getPassword(), member.getPassword())){

                /*UserDetails userDetails = User.builder()
                                .username(member.getUsername())
                                .password(member.getPassword())
                                .roles("USER")
                                .build();

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
*/
                System.out.println("로그인 되었습니다.");
                return true;
            } else {
                System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
            }
        }

        return false;
    }

}
