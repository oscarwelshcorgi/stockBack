package com.pr.board.dto;

import com.pr.board.domain.Board;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String deleteYn;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.email = board.getEmail();
        this.createDate = board.getCreateDate();
        this.updateDate = board.getUpdateDate();
        this.deleteYn = board.getDeleteYn();
    }
}