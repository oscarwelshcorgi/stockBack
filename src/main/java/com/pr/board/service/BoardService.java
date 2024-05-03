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
        // Board 엔티티 리스트를 BoardDto 리스트로 변환하여 반환
        return boardRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 게시글 상세 조회
    public BoardDto getBoardDetail(Long id) {
        // ID로 게시글을 조회하여 BoardDto로 변환하여 반환
        Board board = findBoardById(id);
        return convertToDto(board);
    }

    // 게시글 등록
    public BoardDto createBoard(BoardDto boardDto) {
        // BoardDto를 Board 엔티티로 변환하여 저장
        Board board = convertToEntity(boardDto);
        Board savedBoard = boardRepository.save(board);

        // 저장된 게시글의 ID로 상세 정보 조회하여 반환
        return getBoardDetail(savedBoard.getId());
    }

    // 게시글 수정
    public BoardDto updateBoard(BoardDto boardDto) {
        // ID로 게시글을 조회하여 수정하고 저장 후 BoardDto로 변환하여 반환
        Board board = findBoardById(boardDto.getId());
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        boardRepository.save(board);
        return convertToDto(board);
    }

    // 게시글 삭제
    public void deleteBoard(Long id) {
        // ID로 게시글을 조회하여 삭제
        Board board = findBoardById(id);
        boardRepository.delete(board);
    }

    // 게시글 유무 확인
    private Board findBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
    }

    // Entity -> DTO 변환 메서드
    private BoardDto convertToDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .nickName(board.getNickName())
                .email(board.getEmail())
                .title(board.getTitle())
                .content(board.getContent())
                .createDate(board.getCreateDate())
                .build();
    }

    // DTO -> Entity 변환 메서드
    private Board convertToEntity(BoardDto boardDto) {
        return Board.builder()
                .nickName(boardDto.getNickName())
                .email(boardDto.getEmail())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .createDate(boardDto.getCreateDate()) // 생성일은 클라이언트에서 전달된 값 사용
                .build();
    }
}