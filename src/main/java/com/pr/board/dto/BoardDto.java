package com.pr.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String deleteYn;
}