package com.pr.member.service;

import com.pr.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입 테스트")
    void join() {
        Member member = new Member();
        member.setName("테스트 name");
        member.setEmail("테스트 email");
        member.setPassword("테스트 password");
        member.setPicture("테스트 picture");
        member.setProvider("테스트 provider");
        member.setRole("테스트 role");
        member.setNickName("테스트 nickname");
        member.setCreate_date("2024-03-25 00:00:01");

        String saveEmail = memberService.join(member);

        Optional<Member> optionalMember = memberService.findByEmail(saveEmail);
        Assertions.assertThat(optionalMember.isPresent()).isTrue(); // Optional 객체에 값이 있는지 확인
        Member validateEmail = optionalMember.get();
        Assertions.assertThat(member.getEmail()).isEqualTo(validateEmail.getEmail());

    }

}