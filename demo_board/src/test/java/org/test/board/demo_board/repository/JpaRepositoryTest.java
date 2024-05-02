package org.test.board.demo_board.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;
import org.test.board.demo_board.domain.Article;
import org.test.board.demo_board.domain.Hashtag;
import org.test.board.demo_board.domain.UserAccount;
import org.test.board.demo_board.domain.constant.RoleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import(JpaRepositoryTest.TestJpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;
    private final HashtagRepository hashtagRepository;

    JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository
            , @Autowired ArticleCommentRepository articleCommentRepository
            , @Autowired UserAccountRepository userAccountRepository
            , @Autowired HashtagRepository hashtagRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
        this.userAccountRepository = userAccountRepository;
        this.hashtagRepository = hashtagRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<Article> articles =
                Optional.ofNullable(articleRepository.findAll()).orElseGet(()-> new ArrayList<>());

        // Then
        /*
        assertThat(articles)
                .isEqualTo(List.of()); // classpath:resources/data.sql 참조
        */

        //data에 데이터가 있을 경우
        assertThat(articles)
                .isNotNull()
                .hasSize(1); // classpath:resources/data.sql 참조
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long previousCount = articleRepository.count();
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("id", "pw", Set.of(RoleType.USER), "test@email.com", "nick-name", null));
        Article article = Article.of(userAccount, "new article", "new content");
        article.addHashtags(Set.of(Hashtag.of("spring")));

        // When
        articleRepository.save(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        Hashtag updatedHashtag = Hashtag.of("springboot");
        article.clearHashtags();
        article.addHashtags(Set.of(updatedHashtag));

        // When
        /* saveAndFlush - 실행중(트랜잭션)에 즉시 data를 flush
        *                 -> 즉시 DB 에 변경사항을 적용 */
        Article savedArticle = articleRepository.saveAndFlush(article);

        // Then
        assertThat(savedArticle.getHashtags())
                .hasSize(1)
                .extracting("hashtagName", String.class)
                .containsExactly(updatedHashtag.getHashtagName());
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);
    }

    @DisplayName("회원 정보 delete 테스트")
    @Test
    void givenAdminAccount_whenDeleting_thenWorksFine() {
        // Given
        long previousCount = userAccountRepository.count();
        UserAccount adminAccount = userAccountRepository.getReferenceById("test");

        // When
        userAccountRepository.delete(adminAccount);

        // Then
        assertThat(userAccountRepository.count()).isEqualTo(previousCount - 1);
    }

    /* TODO 댓글 부분 테스트 구현하기 */

    @EnableJpaAuditing
    @TestConfiguration
    static class TestJpaConfig {
        @Bean
        AuditorAware<String> auditorAware() {
            return () -> Optional.of("yuni");
        }
    }

}