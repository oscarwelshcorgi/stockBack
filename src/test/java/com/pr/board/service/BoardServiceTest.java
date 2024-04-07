package com.pr.board.service;

import com.pr.board.domain.Board;
import com.pr.board.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("게시물 목록 조회")
    void boardList() {
        BoardService boardService = new BoardService(boardRepository);
        List<Board> boardList = boardService.getBoardList();
        System.out.println(boardList);
    }

    @Test
    @DisplayName("게시글 작성")
    public void testWrite() {
        // Given
        Board board = new Board();
        board.setTitle("Test Title");
        board.setContent("Test Content");
        board.setEmail("Test Email");
        board.setCreateDate(LocalDateTime.now());
        board.setDeleteYn("n");
        board.setNickName("닉네임 테스트");

        // When
        Board savedBoard = boardRepository.save(board);

        // Then
        assertThat(savedBoard.getId()).isNotNull();
        assertThat(savedBoard.getTitle()).isEqualTo("Test Title");
        assertThat(savedBoard.getContent()).isEqualTo("Test Content");
    }
}