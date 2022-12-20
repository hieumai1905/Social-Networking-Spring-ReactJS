package com.codegym.repository;

import com.codegym.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepo extends JpaRepository<Account, Long> {
    Account findAccountByUsernameAndPassword(String userName, String password);
    Account findAccountByUsername(String username);
    boolean existsAccountByUsername(String username);
    boolean existsAccountByEmail(String email);
    boolean existsAccountByUsernameOrEmail(String username, String email);
}
