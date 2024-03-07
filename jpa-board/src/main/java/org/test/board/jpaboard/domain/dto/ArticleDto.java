package org.test.board.jpaboard.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ArticleDto(
    String title
    , String content
    , String hashtag
    , LocalDateTime createdAt
    , String createdBy
    , LocalDateTime modifiedAt
    , String modifiedBy
) implements Serializable {

    public static ArticleDto of(String title, String content, String hashtag, LocalDateTime createdAt
            , String createdBy, LocalDateTime modifiedAt, String modifiedBy){
        return new ArticleDto(title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

}
