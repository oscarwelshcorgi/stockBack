package com.pr.member.controller;

import com.pr.member.dto.SessionMember;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final HttpSession httpSession;

    @GetMapping("/home")
    public String home(Model model) {
        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        if(member != null){
            model.addAttribute("memberInfo", member);
        }
        return "home";
    }

}
