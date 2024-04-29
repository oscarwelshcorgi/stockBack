package com.pr.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String email;
    private String nickName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String deleteYn;
}