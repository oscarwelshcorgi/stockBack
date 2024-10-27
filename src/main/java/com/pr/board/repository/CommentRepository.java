package com.pr.board.repository;


import com.pr.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleIdAndDeleteYn(Long articleId, String deleteYn);
}