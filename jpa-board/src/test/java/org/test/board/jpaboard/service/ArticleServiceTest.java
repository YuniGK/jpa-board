package org.test.board.jpaboard.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.test.board.jpaboard.domain.Article;
import org.test.board.jpaboard.domain.constant.SearchType;
import org.test.board.jpaboard.domain.dto.ArticleDto;
import org.test.board.jpaboard.repository.ArticleRepository;
import org.test.board.jpaboard.repository.UserAccountRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

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
        Pageable pageable = Pageable.ofSize(20);

        //when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "Search Keyword", pageable);

        //then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
        //given
        Long articleId = 1L;

        //when
        ArticleDto article = sut.searchArticle(articleId);

        //then
        assertThat(article).isNotNull();
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다")
    @Test
    void givenArticleInfo_whenSeavingArtiicle_thenSavesArticle() {
        //given
        ArticleDto dto = ArticleDto.of("title", "content", "hashtag", LocalDateTime.now(), "yuni", LocalDateTime.now(), "yuni");

        given(articleRepository.save(any(Article.class))).willReturn(null);

        //when
        sut.saveArtice(dto);

        //then
        //save메서드가 한번 실행되었는지 확인한다.
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 Id와 수정정보를 입력하면, 게시글을 수정한다")
    @Test
    void givenModifiedArticleInfo_whenUpdatingArticle_thenUpdatesArticle() {
        //given
        ArticleDto dto = ArticleDto.of("title", "content", "hashtag", LocalDateTime.now(), "yuni", LocalDateTime.now(), "yuni");

        given(articleRepository.save(any(Article.class))).willReturn(null);

        //when
        sut.updateArtice(1L, dto);

        //then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 id를 입력하면 게시글을 삭제한다")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
        //given
        ArticleDto dto = ArticleDto.of("title", "content", "hashtag", LocalDateTime.now(), "yuni", LocalDateTime.now(), "yuni");

        //willDoNothing == doNothing
        willDoNothing().given(articleRepository).delete(any(Article.class));

        //when
        sut.deleteArtice(1L);

        //then
        then(articleRepository).should().delete(any(Article.class));
    }

}