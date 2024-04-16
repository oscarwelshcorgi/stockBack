package com.pr.board.service;

import com.pr.board.domain.Board;
import com.pr.board.dto.BoardDto;
import com.pr.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDto> getBoardList() {
        List<Board> boardEntities = boardRepository.findAll();
        List<BoardDto> dtos = new ArrayList<>();

        for (Board board : boardEntities) {
            BoardDto dto = BoardDto.builder()
                    .id(board.getId())
                    .nickName(board.getNickName())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createDate(board.getCreateDate())
                    .build();

            dtos.add(dto);
        }

        return dtos;
    }

    public Long write(Board board){

        boardRepository.save(board);

        return board.getId();
    }


    public Board findById(Long id) {
        return boardRepository.findById(id).orElse(null); // ID로 게시글 조회
    }

}
