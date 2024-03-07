package org.test.board.jpaboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.test.board.jpaboard.domain.Article;

@RepositoryRestResource//컨트롤러를 만들지 않아도, 내부적으로 Rest API를 만들 수 있다.
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
