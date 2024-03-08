package org.test.board.jpaboard.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ArticleDto(
        Long id
        , String title
        , String content
        , String hashtag
        , LocalDateTime createdAt
        , String createdBy
        , LocalDateTime modifiedAt
        , String modifiedBy
){

    public static ArticleDto of(String title, String content, String hashtag, LocalDateTime createdAt
            , String createdBy, LocalDateTime modifiedAt, String modifiedBy){
        return new ArticleDto(null, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleDto of(Long id, String title, String content, String hashtag, LocalDateTime createdAt
            , String createdBy, LocalDateTime modifiedAt, String modifiedBy){
        return new ArticleDto(id, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

}
