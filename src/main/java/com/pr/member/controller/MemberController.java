package com.pr.member.controller;

import com.pr.member.dto.SessionMember;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final HttpSession httpSession;

    @GetMapping("/memberInfo")
    public SessionMember getMemberInfo(Model model) {
        SessionMember member = (SessionMember) httpSession.getAttribute("member");
        System.out.println("aaaaaaaaaaaaa MEMBERINFO@!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + member);
        return member; // JSON 형식의 응답으로 반환됨
    }
}
