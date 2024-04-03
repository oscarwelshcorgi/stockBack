package com.pr.board.service;

import com.pr.board.domain.Board;
import com.pr.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void boardList() {
        // 게시물 목록 조회
        BoardService boardService = new BoardService(boardRepository);
        List<Board> boardList = boardService.getBoardList();
        System.out.println(boardList);
    }

}