package org.test.board.jpaboard.domain;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Data
public class Article {
    private Long id;
    private String title;
    private String hashtag;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
