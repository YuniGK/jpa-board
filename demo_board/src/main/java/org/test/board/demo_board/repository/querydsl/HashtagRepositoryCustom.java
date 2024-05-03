package org.test.board.demo_board.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.test.board.demo_board.domain.Article;

import java.util.Collection;
import java.util.List;

public interface HashtagRepositoryCustom {
    List<String> findAllHashtagNames();
}
