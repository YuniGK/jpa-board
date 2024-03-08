package org.test.board.jpaboard.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@ToString(callSuper = true, exclude = "articleComments")
@Table(indexes = {
        @Index(columnList = "title")
        , @Index(columnList = "createdAt")
        , @Index(columnList = "createdBy")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)//기본 생성자
public class Article extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false)
    private String title;
    @Setter @Column(nullable = false, length = 10000)
    private String content;
    @Setter
    private String hashtag;

    @Setter @ManyToOne(optional = false) @JoinColumn(name = "userId")
    private UserAccount userAccount;

    /* 댓글 -> 글 참조 OK, 글에서 댓글 리스트 뽑는 것은 비효율적이기에, 해당 부분에서 끊어준다. */
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    //도메인 Article 생성시 사용
    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        return new Article(userAccount, title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
