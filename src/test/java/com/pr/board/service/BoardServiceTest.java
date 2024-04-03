package com.pr.board.service;

import com.pr.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardRepository boardRepository;

    void boardList() {
        boardRepository.findAllByIdDesc();
    }

}