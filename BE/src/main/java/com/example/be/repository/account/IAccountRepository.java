package com.example.be.repository.account;

import com.example.be.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IAccountRepository extends JpaRepository<Account, Integer> {

    @Query(
            value = " select * " +
                    " from account " +
                    " where username = :username and delete_status = false ",
            nativeQuery = true
    )
    Account findAccountByUsername(@Param("username") String username);

    Optional<Account> findByUsername (String name);

    Boolean existsByUsername ( String username);

    @Modifying
    @Query(value = "update account set password = :password where username = :username", nativeQuery = true)
    void changePassword(@Param("password") String password, @Param("username") String username);

    @Query(value = "select a.id from account a where id in (:idList) and delete_status = false ",nativeQuery = true)
    List<Integer> findByListIdAccount(@Param("idList") List<Integer> idList);

    @Query(value = "select a.id from account a where id in (:idList) and delete_status = true ",nativeQuery = true)
    List<Integer> findByListIdAccountRestore(@Param("idList") List<Integer> idList);

    @Modifying
    @Query(value = "update account a set a.delete_status = true where id in (:idList)", nativeQuery = true)
    void removeByListIdAccount(@Param("idList") List<Integer> idList);

    @Modifying
    @Query(value = "update account a set a.delete_status = false where id in (:idList)", nativeQuery = true)
    void restoreByListIdAccount(@Param("idList") List<Integer> idList);
}
