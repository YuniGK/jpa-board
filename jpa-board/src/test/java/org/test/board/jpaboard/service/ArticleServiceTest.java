package org.test.board.jpaboard.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.test.board.jpaboard.domain.constant.SearchType;
import org.test.board.jpaboard.domain.dto.ArticleDto;
import org.test.board.jpaboard.repository.ArticleRepository;
import org.test.board.jpaboard.repository.UserAccountRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks
    private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private UserAccountRepository userAccountRepository;

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        //given

        //when
        List<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "Search Keyword");

        //then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
        //given
        Long articleId = 1L;

        //when
        ArticleDto articles = sut.searchArticles(articleId);

        //then
        assertThat(articles).isNotNull();
    }

}