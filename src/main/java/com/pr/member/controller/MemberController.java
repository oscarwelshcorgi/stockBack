package com.pr.member.controller;

import com.pr.member.domain.MemberInfo;
import com.pr.member.dto.SessionMember;
import com.pr.member.repository.MemberRepository;
import com.pr.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class MemberController {

    private final HttpSession httpSession;
    private final MemberRepository memberRepository;

    @GetMapping("/memberInfo")
    public SessionMember getMemberInfo() {
        SessionMember sessionMember = (SessionMember) httpSession.getAttribute("memberInfo");

        // 세션 값이 있을 시, 닉네임 체크
        if (sessionMember != null) {
            Optional<MemberInfo> memberInfo = memberRepository.findByEmail(sessionMember.getEmail());
            // memberInfo 객체가 값이 있을 때, 그리고 닉네임이 null이거나 공백일 때
            if (memberInfo.isPresent() && (memberInfo.get().getNickName() == null || memberInfo.get().getNickName().isEmpty())) {
                sessionMember.setCheckNickName(true);
            } else {
                sessionMember.setCheckNickName(false);
            }
            System.out.println("@@로그인한 맴버 정보: " + memberInfo);
            return sessionMember;
        }
        return null;
    }
}
