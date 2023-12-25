package com.example.be.service;


import com.example.be.model.account.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IAccountService {

    Account findAccountByUsername(String username);

    Account createAccount(Account account);

    Account findById(Integer id);

    Account updateAccount(Account account);

    Boolean existsByUsername(String username);

    Optional<Account> findByName(String username);

    void updatePassword(String password, String username);

    List<Integer> findByListIdAccount(List<Integer> idList);

    @Transactional
    void removeByListIdAccount(List<Integer> idList);

}
