package org.test.board.demo_board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.board.demo_board.domain.constant.SearchType;
import org.test.board.demo_board.dto.ArticleDto;
import org.test.board.demo_board.repository.ArticleRepository;
import org.test.board.demo_board.repository.HashtagRepository;
import org.test.board.demo_board.repository.UserAccountRepository;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
    private final HashtagService hashtagService;
    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;
    private final HashtagRepository hashtagRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtagNames(
                            Arrays.stream(searchKeyword.split(" ")).toList(),
                            pageable
                    )
                    .map(ArticleDto::from);
        };
    }
}
