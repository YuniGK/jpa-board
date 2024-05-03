package org.test.board.demo_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.test.board.demo_board.domain.Article;
import org.test.board.demo_board.domain.ArticleComment;
import org.test.board.demo_board.domain.QArticle;
import org.test.board.demo_board.domain.projection.ArticleProjection;
import org.test.board.demo_board.repository.querydsl.ArticleRepositoryCustom;

/*
* @RepositoryRestResource : repository 선언에 대한 어노테이션
* ①collectionResourceRel : 반환되는 데이터 배열의 이름
* ② path : 경로- jpa 와 같이 선언만으로도 기본적인 CRUD 가 만들어집니다. 여기서 차이는 controller가 필요 없다는 것 입니다.
* - /{repository} 이런 식으로 맵핑이 되기 때문입니다.
*
* https://docs.spring.io/spring-data/rest/docs/3.0.5.RELEASE/reference/html/#getting-started.basic-settings
*
* @RepositoryRestResource(excerptProjection = Projection)
* - @RepositoryRestResource의 excerptProjection으로 상시 설정할 Projection을 걸어 둘 수 있다.
* */
@RepositoryRestResource(excerptProjection = ArticleProjection.class)
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        ArticleRepositoryCustom,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {
}
