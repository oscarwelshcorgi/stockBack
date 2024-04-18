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
        Board board = findBoardById(id);
        return convertToDto(board);
    }

    // 게시글 등록
    public BoardDto getBoardCreate(BoardDto boardDto) {
        // 게시글 생성
        Board board = convertToEntity(boardDto);
        Board savedBoard = boardRepository.save(board);

        // 생성된 게시글의 ID로 상세 정보 조회
        return getBoardDetail(savedBoard.getId());
    }

    // 게시글 수정
    public BoardDto getBoardUpdate(BoardDto boardDto) {
        Board board = findBoardById(boardDto.getId());
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        boardRepository.save(board);
        return convertToDto(board);
    }



    // 게시글 삭제
    public void delete(Long id) {
        Board board = findBoardById(id);
        boardRepository.delete(board);
    }

    //게시글 유무 확인(id가 있는지)
    private Board findBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
    }

    // Entity -> DTO (DB에서 조회한 엔티티 객체를 클라이언트로 전송하기 위해 DTO 객체로 변환)
    private BoardDto convertToDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .nickName(board.getNickName())
                .title(board.getTitle())
                .content(board.getContent())
                .createDate(board.getCreateDate())
                .build();
    }

    // DTO -> Entity
    private Board convertToEntity(BoardDto boardDto) {
        return Board.builder()
                .id(boardDto.getId())
                .nickName(boardDto.getNickName())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .createDate(boardDto.getCreateDate())
                .build();
    }
}