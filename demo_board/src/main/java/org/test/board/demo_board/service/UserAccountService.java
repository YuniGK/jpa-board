package org.test.board.demo_board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.test.board.demo_board.domain.UserAccount;
import org.test.board.demo_board.domain.constant.RoleType;
import org.test.board.demo_board.dto.UserAccountDto;
import org.springframework.transaction.annotation.Transactional;
import org.test.board.demo_board.repository.UserAccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String username) {
        return userAccountRepository.findById(username)
                .map(UserAccountDto::from);
    }

    public UserAccountDto saveUser(String userId, String userPassword, Set<RoleType> roleTypes
            , String email, String nickname, String memo) {
        return UserAccountDto.from(
                userAccountRepository.save(UserAccount.of(userId, userPassword, roleTypes, email, nickname, memo, userId))
        );
    }

    public void deleteUser(String userId) {
    }

    public List<UserAccountDto> users() {
    }
}
