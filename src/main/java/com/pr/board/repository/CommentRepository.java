package com.pr.board.repository;

import com.pr.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Article id를 기준으로 댓글 조회
    List<Comment> findByArticleId(Long articleId);
}
