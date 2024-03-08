package org.test.board.jpaboard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.util.Objects;

@Entity
@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content")
        , @Index(columnList = "createdAt")
        , @Index(columnList = "createdBy")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleComment extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false)
    private Article article;

    @Setter @JoinColumn(name = "userId") @ManyToOne(optional = false)
    private UserAccount userAccount;

    @Setter @Column(nullable = false, length = 500)
    private String content;

    private ArticleComment(Article article, UserAccount userAccount, String content) {
        this.article = article;
        this.userAccount = userAccount;
        this.content = content;
    }

    public static ArticleComment of(Article article, UserAccount userAccount, String content) {
        return new ArticleComment(article, userAccount, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
