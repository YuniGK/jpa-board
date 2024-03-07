package org.test.board.jpaboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.board.jpaboard.domain.constant.SearchType;
import org.test.board.jpaboard.domain.dto.ArticleDto;
import org.test.board.jpaboard.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleDto> searchArticles(SearchType searchType, String searchKeyword) {
        return List.of();
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticles(Long articleId) {
        return null;
    }
}
