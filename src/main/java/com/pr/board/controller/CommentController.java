package com.pr.board.controller;

import com.pr.board.domain.Comment;
import com.pr.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * articleId를 기준으로 댓글 조회
     *
     * @param articleId 조회할 article의 ID
     * @return 해당 article에 속한 모든 댓글 목록
     */
    @GetMapping("/{id}/{articleId}")
    public ResponseEntity<List<Comment>> getCommentsByArticleId(@PathVariable Long articleId) {
        List<Comment> comments = commentService.getCommentsByArticleId(articleId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 생성, 수정, 삭제 API 등 추가 가능
}
