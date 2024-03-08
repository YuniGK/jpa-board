package org.test.board.jpaboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.board.jpaboard.domain.constant.SearchType;
import org.test.board.jpaboard.domain.dto.ArticleDto;
import org.test.board.jpaboard.repository.ArticleRepository;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(Long articleId) {
        return null;
    }

    public void saveArtice(ArticleDto dto) {
    }

    public void updateArtice(long articleId, ArticleDto dto) {
    }

    public void deleteArtice(long articleId) {
    }
}
