package com.pr.board.service;

import com.pr.board.domain.Board;
import com.pr.board.dto.BoardDto;
import com.pr.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 리스트 조회
    public List<BoardDto> getBoardList() {
        List<BoardDto> dtos = boardRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return dtos;
    }

    // 게시글 상세 조회
    public BoardDto getBoardDetail(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        return convertToDto(board);
    }

    private BoardDto convertToDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .nickName(board.getNickName())
                .title(board.getTitle())
                .content(board.getContent())
                .createDate(board.getCreateDate())
                .build();
    }
}