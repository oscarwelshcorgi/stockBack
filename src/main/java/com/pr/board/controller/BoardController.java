package com.pr.board.controller;

import com.pr.board.domain.Board;
import com.pr.board.dto.BoardDto;
import com.pr.board.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class BoardController {

    private final BoardService boardService;

    private final HttpSession httpSession;

    //게시판 리스트 가져오기
    @RequestMapping(value="/board/list", method=RequestMethod.GET)
    public List<BoardDto> boardList() {
        return boardService.getBoardList();
    }

    @RequestMapping(value="/board/detail/{id}", method=RequestMethod.GET)
    public BoardDto boardDetail(@PathVariable("id") Long id) {
        return boardService.getBoardDetail(id);
    }
}
