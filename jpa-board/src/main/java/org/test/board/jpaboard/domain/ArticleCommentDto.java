package org.test.board.jpaboard.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ArticleCommentDto(
     Article article
    , String content
    , LocalDateTime createdAt
    , String createdBy
    , LocalDateTime modifiedAt
    , String modifiedBy
) implements Serializable {
}
