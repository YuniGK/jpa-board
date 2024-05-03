package org.test.board.demo_board.domain.projection;

import org.springframework.data.rest.core.config.Projection;
import org.test.board.demo_board.domain.Article;
import org.test.board.demo_board.domain.UserAccount;

import java.time.LocalDateTime;

/* @Projection - DB의 필요한 속성(조회시 원하는 컬럼을 가져오는 방식)만을 조회 */
@Projection(name ="withUserAccount", types = Article.class)
public interface ArticleProjection {
    Long getId();
    UserAccount getUserAccount();
    String getTitle();
    String getContent();
    LocalDateTime getCreatedAt();
    String getCreatedBy();
    LocalDateTime getModifiedAt();
    String getModifiedBy();
}