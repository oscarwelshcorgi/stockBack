package com.pr.member.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String join() {
        return "db 연결 후 test";
    }
}
