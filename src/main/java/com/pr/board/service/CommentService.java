package com.pr.board.service;

import com.pr.board.domain.Article;
import com.pr.board.domain.Comment;
import com.pr.board.repository.ArticleRepository;
import com.pr.board.repository.CommentRepository;
import com.pr.member.domain.MemberInfo;
import com.pr.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Comment> getCommentsByArticleId(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleIdAndDeleteYn(articleId, "N");
        for (Comment comment : comments) {
            MemberInfo memberInfo = memberRepository.findByEmail(comment.getEmail())
                    .orElse(null);
            if (memberInfo != null) {
                comment.setNickName(memberInfo.getNickName());
            }
        }
        return comments;
    }

    public Comment addComment(Comment comment) {
        Article article = articleRepository.findById(comment.getArticle().getId())
                .orElseThrow(() -> new NoSuchElementException("Article with ID " + comment.getArticle().getId() + " not found"));
        comment.setArticle(article);
        return commentRepository.save(comment);
    }
}