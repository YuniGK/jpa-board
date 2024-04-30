package org.test.board.demo_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.board.demo_board.domain.Article;
import org.test.board.demo_board.domain.ArticleComment;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
