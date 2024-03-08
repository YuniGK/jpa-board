package org.test.board.jpaboard.domain.dto;

import org.test.board.jpaboard.domain.Article;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id
        , Long articleId
        , String content
        , LocalDateTime createdAt
        , String createdBy
        , LocalDateTime modifiedAt
        , String modifiedBy
) {

    public static ArticleCommentDto of(Long articleId, String content) {
        return ArticleCommentDto.of(articleId, content, null, null, null, null);
    }

    public static ArticleCommentDto of(Long articleId, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return ArticleCommentDto.of(articleId, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }
}
