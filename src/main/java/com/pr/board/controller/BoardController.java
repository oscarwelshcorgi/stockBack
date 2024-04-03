package com.pr.board.controller;

import com.pr.board.domain.Board;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BoardController {

    @GetMapping("/board/list")
    public String boardList() {
        return "board";
    }
}
