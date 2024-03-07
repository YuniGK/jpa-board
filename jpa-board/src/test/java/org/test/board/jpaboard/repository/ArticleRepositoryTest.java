package org.test.board.jpaboard.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.test.board.jpaboard.config.JpaConfig;
import org.test.board.jpaboard.domain.Article;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/*
테스트 DB를 불러오지 않고 설정되어 있는 걸 사용
@ActiveProfiles("testDB")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

위와 같이 사용하고 싶지 않을 경우 yaml에서 설정이 가능하다.
spring:
  config.activate.on-profile: testDB
  datasource:
    url: jdbc:h2:mem:board;mode=mysql
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa.hibernate.ddl-auto: create
  sql.init.mode: always
  test.database.replace: none

  test.database.replace: none 가 해당 부분에 해당하는 내용이다.
*/
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class ArticleRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public ArticleRepositoryTest(@Autowired ArticleRepository articleRepository
            , @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTest_whenSelect_thenWorks(){
        //Given

        //When
        List<Article> articles = articleRepository.findAll();

        //Then
        assertThat(articles)
                .isNotNull()
                .hasSize(0);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTest_whenInsert_thenWorks(){
        //Given
        Long previousCount = articleRepository.count();
        Article article = Article.of("title", "content", "hashtag");

        //When
        Article savedArticle = articleRepository.saveAndFlush(article);

        //Then
        assertThat(articleRepository.count()).isEqualTo(previousCount+1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTest_whenUpdate_thenWorks(){
        //Given
        Article savedArticle = articleRepository.save(Article.of("title", "content", "hashtag"));

        Article article = articleRepository.findById(1L).orElseThrow();

        String updatedHashtag = "new hashtag";
        article.setHashtag(updatedHashtag);

        //When
        Article articles = articleRepository.save(article);

        //Then
        assertThat(articles)
                .hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTest_whenDelete_thenWorks(){
        //Given
        Article savedArticle = articleRepository.save(Article.of("title", "content", "hashtag"));

        Article article = articleRepository.findById(1L).orElseThrow();

        Long previousArticleCount = articleRepository.count();
        Long previousArticleCommentCount = articleCommentRepository.count();

        //해당 게시글에 해당하는 댓글의 갯수 확인
        int deletedCommentsSize = article.getArticleComments().size();

        //When
        articleRepository.delete(article);

        //Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
                                                                //전체 댓글 - 게시글 삭제에 따른 댓글 삭제 목록
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount-deletedCommentsSize);
    }
}