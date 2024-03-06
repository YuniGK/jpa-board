package org.test.board.jpaboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.board.jpaboard.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
