package com.pr.board.service;

import com.pr.board.domain.Board;
import com.pr.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    public Long write(Board board){

        boardRepository.save(board);

        return board.getId();
    }


    public Board findById(Long id) {
        return boardRepository.findById(id).orElse(null); // ID로 게시글 조회
    }

}
