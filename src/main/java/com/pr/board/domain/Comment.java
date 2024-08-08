package com.pr.board.domain;

import com.pr.member.domain.MemberInfo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comment")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId", referencedColumnName = "id")
    private Long articleId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "commentDeleteYn", nullable = false, length = 5)
    private String commentDeleteYN;

    @Column(name = "createDate", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "updateDate", nullable = false)
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
    private MemberInfo memberInfo;

    @Builder
    public Comment(Long id, Long articleId, String content, String email, String commentDeleteYN, LocalDateTime createDate, LocalDateTime updateDate, MemberInfo memberInfo) {
        this.id = id;
        this.articleId = articleId;
        this.content = content;
        this.email = email;
        this.commentDeleteYN = commentDeleteYN;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.memberInfo = memberInfo;
    }

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now(); // 엔티티가 저장되기 전에 작성 날짜 설정
        this.commentDeleteYN = "n";
    }
}