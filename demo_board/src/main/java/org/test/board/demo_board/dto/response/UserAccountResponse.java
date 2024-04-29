package org.test.board.demo_board.dto.response;

import org.test.board.demo_board.domain.constant.RoleType;
import org.test.board.demo_board.dto.UserAccountDto;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public record UserAccountResponse(
        String userId,
        String roleTypes,
        String email,
        String nickname,
        String memo,
        String provider,
        String provideId,
        LocalDateTime createdAt,
        String createdBy
) {
    public static UserAccountResponse of(String userId, String roleTypes, String email, String nickname
        , String memo, String provider, String provideId, LocalDateTime createdAt, String createdBy){
        return new UserAccountResponse(userId, roleTypes, email, nickname, memo, provider, provideId, createdAt, createdBy);
    }

    public static UserAccountResponse from(UserAccountDto dto){
        return UserAccountResponse.of(
                dto.userId()
                , dto.roleTypes().stream()
                        .map(RoleType::getDescription)
                        .collect(Collectors.joining(", "))
                , dto.email()
                , dto.nickname()
                , dto.memo()
                , dto.provider()
                , dto.provideId()
                , dto.createdAt()
                , dto.createdBy()
        );
    }
}
