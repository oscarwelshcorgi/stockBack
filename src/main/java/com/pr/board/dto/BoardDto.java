package com.pr.board.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String email;

    private String nickName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String deleteYn;

    public BoardDto(Long id, String title, String content, String email, String name, String nickName, LocalDateTime createDate, LocalDateTime updateDate, String deleteYn) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.email = email;
        this.nickName = nickName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteYn = deleteYn;
    }
}