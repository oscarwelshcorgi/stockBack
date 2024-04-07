package com.pr.board.controller;

import com.pr.board.domain.Board;
import com.pr.board.dto.BoardDto;
import com.pr.board.service.BoardService;
import com.pr.member.dto.SessionMember;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final HttpSession httpSession;
    @GetMapping("/board/list")
    public String boardList(Model model) {
        // 게시판 리스트 가져오기
        model.addAttribute("boardList", boardService.getBoardList());
        return "board";
    }

    @GetMapping("/board/write")
    public String write(Model model) {
        SessionMember member = (SessionMember) httpSession.getAttribute("member");
        model.addAttribute("member", member);
        return "write";
    }

    @PostMapping("/board/writePost")
    public String writePost(BoardDto boardDto) {

        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setCreateDate(LocalDateTime.now());
        board.setDeleteYn("N");
        board.setEmail(member.getEmail());
        board.setNickName(member.getNickName());

        // 게시글을 저장하고 저장된 게시글의 ID를 반환
        Long id = boardService.write(board);

        return "redirect:/board/" + id;
    }

    @GetMapping("/board/detail/{id}")
    public String showBoardDetails(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "detail";
    }
}
