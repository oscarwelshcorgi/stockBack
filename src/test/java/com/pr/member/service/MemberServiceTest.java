package com.pr.member.service;

import com.pr.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입 테스트1")
    void join() {
        Member member = new Member();
        member.setName("테스트 name2");
        member.setEmail("테스트 email2");
        member.setPassword("테스트 password2");
        member.setPicture("테스트 picture2");
        member.setProvider("테스트 provider2");
        member.setRole("테스트 role2");
        member.setNickName("테스트 nickname2");
        member.setCreate_date("2024-03-25 00:00:01");

        String saveEmail = memberService.join(member);

        Optional<Member> optionalMember = memberService.findByEmail(saveEmail);
        Assertions.assertThat(optionalMember.isPresent()).isTrue(); // Optional 객체에 값이 있는지 확인
        Member validateEmail = optionalMember.get();
        Assertions.assertThat(member.getEmail()).isEqualTo(validateEmail.getEmail());
    }

    @Test
    @DisplayName("전체 회원 리스트 조회 테스트")
    void findMembers() {
        List<Member> memberList = memberService.findMembers();
        System.out.println(memberList);
    }

    @Test
    @DisplayName("회원 검색")
    void fincByEmail() {
        Member member = new Member();
        member.setEmail("테스트 email");

        String findEmail = String.valueOf(memberService.findByEmail(String.valueOf(member)));

    }

}