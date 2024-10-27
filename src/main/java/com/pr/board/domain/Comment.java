package com.pr.board.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comment")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    private String email;
    private String commentContent;

    @CreationTimestamp
    private LocalDateTime createDate;

    private String deleteYn = "N";

    @Transient // 데이터베이스에는 저장되지 않는 필드, 조회 시에만 설정됨
    private String nickName;
}