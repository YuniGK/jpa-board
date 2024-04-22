package org.test.board.demo_board.domain;

import jakarta.persistence.*;
import lombok.*;
import org.test.board.demo_board.domain.constant.RoleType;
import org.test.board.demo_board.domain.converter.RoleTypesConverter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserAccount extends AuditingFields{
    @Id
    @Column(length = 50)
    private String userId;

    @Setter @Column(nullable = false) private String userPassword;

    @Convert(converter = RoleTypesConverter.class)
    @Column(nullable = false)
    private Set<RoleType> roleTypes = new LinkedHashSet<>();

    @Setter @Column(length = 100) private String email;
    @Setter @Column(length = 100) private String nickname;
    @Setter
    private String memo;

    /* 참고 : https://velog.io/@rnqhstlr2297/Spring-Security-OAuth2-%EC%86%8C%EC%85%9C%EB%A1%9C%EA%B7%B8%EC%9D%B8*/
    private String provider; //어떤 OAuth인지(google, naver 등)
    private String provideId; // 해당 OAuth 의 key(id)

    private UserAccount(String userId, String userPassword, Set<RoleType> roleTypes
            , String email, String nickname, String memo, String provider, String provideId
            , String createdBy) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.roleTypes = roleTypes;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
        this.provider = provider;
        this.provideId = provideId;
        this.createdBy = createdBy;
    }

    public static UserAccount of(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo) {
        return UserAccount.of(userId, userPassword, roleTypes, email, nickname, memo, null, null, null);
    }

    public static UserAccount of(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo
            , String provider, String provideId, String createdBy) {
        return new UserAccount(userId, userPassword, roleTypes, email, nickname, memo, provider, provideId, createdBy);
    }

    /* --- */
    public void addRoleType(RoleType roleType) {
        this.getRoleTypes().add(roleType);
    }

    public void addRoleTypes(Collection<RoleType> roleTypes) {
        this.getRoleTypes().addAll(roleTypes);
    }

    public void removeRoleType(RoleType roleType) {
        this.getRoleTypes().remove(roleType);
    }
    /* --- */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return this.getUserId() != null && this.getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId());
    }
}
