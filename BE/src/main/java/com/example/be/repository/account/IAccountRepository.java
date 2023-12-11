package com.example.be.repository.account;

import com.example.be.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface IAccountRepository extends JpaRepository<Account, Integer> {

    @Query(
            value = " select * " +
                    " from account " +
                    " where username = :username ",
            nativeQuery = true
    )
    Account findAccountByUsername(@Param("username") String username);

    Optional<Account> findByUsername (String name);

    Boolean existsByUsername ( String username);

    @Modifying
    @Query(value = "update account set password = :password where username = :username", nativeQuery = true)
    void changePassword(@Param("password") String password, @Param("username") String username);
}
