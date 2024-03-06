package org.test.board.jpaboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.board.jpaboard.domain.ArticleComment;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
