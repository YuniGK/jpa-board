package org.test.board.demo_board.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.test.board.demo_board.domain.UserAccount;
import org.test.board.demo_board.domain.constant.RoleType;
import org.test.board.demo_board.dto.UserAccountDto;
import org.test.board.demo_board.repository.UserAccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 회원")
/* 단위 테스트에 공통적으로 사용할 확장 기능을 선언 */
@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {
    /* @InjectMocks - @Mock 또는 @Spy로 생성된 가짜 객체를 자동으로 주입 */
    @InjectMocks private UserAccountService sut;

    @Mock
    private UserAccountRepository userAccountRepository;

    @DisplayName("존재하는 회원 ID를 검색하면, 회원 데이터를 Optional로 반환한다.")
    @Test
    void givenExistentUserId_whenSearching_thenReturnsOptionalUserData() {
        // Given
        String username = "test";

                                                        /* willReturn(false) - 해당 메서드가 반환하는 값 */
        given(userAccountRepository.findById(username)).willReturn(Optional.of(createUserAccount(username)));

        // When
        Optional<UserAccountDto> result = sut.searchUser(username);

        // Then
        /* isPresent
        * - Boolean 타입
        * - Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴 */
        assertThat(result).isPresent();
        then(userAccountRepository).should().findById(username);
    }

    @DisplayName("존재하지 않는 회원 ID를 검색하면, 비어있는 Optional을 반환한다.")
    @Test
    void givenNonexistentUserId_whenSearching_thenReturnsOptionalUserData() {
        // Given
        String username = "wrong-user";
        given(userAccountRepository.findById(username)).willReturn(Optional.empty());

        // When
        Optional<UserAccountDto> result = sut.searchUser(username);

        // Then
        /* isEmpty() / isBlank()
        * isEmpty() - 문자열의 길이가 0인 경우에, true
        * isBlank() - 문자열이 비어 있거나, 빈 공백으로만 이루어져 있으면, true */
        assertThat(result).isEmpty();
        then(userAccountRepository).should().findById(username);
    }

    @DisplayName("회원 정보를 입력하면, 새로운 회원 정보를 저장하여 가입시키고 해당 회원 데이터를 리턴한다.")
    @Test
    void givenUserParams_whenSaving_thenSavesUserAccount() {
        // Given
        UserAccount userAccount = createUserAccount("test");
        UserAccount savedUserAccount = createSigningUpUserAccount("test", Set.of(RoleType.USER));
        given(userAccountRepository.save(userAccount)).willReturn(savedUserAccount);

        // When
        UserAccountDto result = sut.saveUser(
                userAccount.getUserId(),
                userAccount.getUserPassword(),
                userAccount.getRoleTypes(),
                userAccount.getEmail(),
                userAccount.getNickname(),
                userAccount.getMemo()
        );

        // Then
        /* assertThat 사용할 경우 위에서 부터 실행 하나가 실패 할 경우 남은 부분 검증하지 않는다.
        * SoftAssertions 사용할 경우 중간에 실패해도 남은 부분을 검증한다.

        * 예 ]
        * assertSoftly(softly -> {
            softly.assertThat(result.nickname()).isEqualTo(userAccount.getNickname());
        });
         */

        assertThat(result) /* hasFieldOrPropertyWithValue - userId의 값은 userAccount.getUserId() 같다 */
                .hasFieldOrPropertyWithValue("userId", userAccount.getUserId())
                .hasFieldOrPropertyWithValue("userPassword", userAccount.getUserPassword())
                .hasFieldOrPropertyWithValue("roleTypes", userAccount.getRoleTypes())
                .hasFieldOrPropertyWithValue("email", userAccount.getEmail())
                .hasFieldOrPropertyWithValue("nickname", userAccount.getNickname())
                .hasFieldOrPropertyWithValue("memo", userAccount.getMemo())
                .hasFieldOrPropertyWithValue("createdBy", userAccount.getUserId())
                .hasFieldOrPropertyWithValue("modifiedBy", userAccount.getUserId());
        then(userAccountRepository).should().save(userAccount);
    }

    @DisplayName("전체 어드민 회원을 조회한다.")
    @Test
    void givenNothing_whenSelectingAdminAccounts_thenReturnsAllAdminAccounts() {
        // Given
        given(userAccountRepository.findAll()).willReturn(List.of());

        // When
        List<UserAccountDto> result = sut.users();

        // Then
        assertThat(result).hasSize(0);
        then(userAccountRepository).should().findAll();
    }

    @DisplayName("회원 ID를 입력하면, 회원을 삭제한다.")
    @Test
    void givenUserId_whenDeleting_thenDeletesAdminAccount() {
        // Given
        String userId = "test";
        willDoNothing().given(userAccountRepository).deleteById(userId);

        // When
        sut.deleteUser(userId);

        // Then
        then(userAccountRepository).should().deleteById(userId);
    }

    private UserAccount createUserAccount(String username) {
        return createUserAccount(username, Set.of(RoleType.USER), null);
    }

    private UserAccount createSigningUpUserAccount(String username, Set<RoleType> roleTypes) {
        return createUserAccount(username, roleTypes, username);
    }

    private UserAccount createUserAccount(String username, Set<RoleType> roleTypes, String createdBy) {
        return UserAccount.of(
                username,
                "password",
                roleTypes,
                "e@mail.com",
                "nickname",
                "memo",
                createdBy
        );
    }
}