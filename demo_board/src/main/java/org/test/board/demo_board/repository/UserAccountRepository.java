package org.test.board.demo_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.board.demo_board.domain.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String>{
}
