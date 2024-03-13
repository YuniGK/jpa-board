package org.test.board.jpaboard.domain.dto.request;

import org.test.board.jpaboard.domain.dto.ArticleCommentDto;
import org.test.board.jpaboard.domain.dto.ArticleDto;
import org.test.board.jpaboard.domain.dto.UserAccountDto;

public record ArticleCommentRequest(Long articleId, String content) {

    public static ArticleCommentRequest of(Long articleId, String content) {
        return new ArticleCommentRequest(articleId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                content
        );
    }

}