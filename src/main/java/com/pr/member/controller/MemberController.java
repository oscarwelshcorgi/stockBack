package com.pr.member.controller;

import com.pr.member.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MemberController {

    private HttpSession httpSession;

    public MemberController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String home(Model model) {
        SessionUser member = (SessionUser) httpSession.getAttribute("member");

        if(member != null){
            model.addAttribute("member11", member);
        }
        return "home";
    }

}
