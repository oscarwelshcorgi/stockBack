package com.pr.board.service;

import com.pr.board.domain.Comment;
import com.pr.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * articleId를 기준으로 댓글 조회
     *
     * @param articleId 조회할 article의 ID
     * @return 해당 article에 속한 모든 댓글 목록
     */
    public List<Comment> getCommentsByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    /**
     * 댓글 생성
     *
     * @param comment 생성할 댓글 정보
     * @return 생성된 댓글
     */
    public Comment createComment(Comment comment) {
        // create_date와 update_date는 자동으로 설정됩니다.
        return commentRepository.save(comment);
    }

    /**
     * 댓글 수정
     *
     * @param id              수정할 댓글 ID
     * @param updatedComment  수정된 댓글 정보
     * @return 수정된 댓글
     * @throws IllegalArgumentException 해당 ID를 가진 댓글이 없는 경우 예외 발생
     */
    public Comment updateComment(Long id, Comment updatedComment) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();
            existingComment.setArticleId(updatedComment.getArticleId());
            existingComment.setContent(updatedComment.getContent());
            existingComment.setEmail(updatedComment.getEmail());
            existingComment.setCommentDeleteYN(updatedComment.getCommentDeleteYN());
            return commentRepository.save(existingComment);
        } else {
            throw new IllegalArgumentException("Comment not found with id: " + id);
        }
    }

    /**
     * 댓글 삭제
     *
     * @param id 삭제할 댓글 ID
     */
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}