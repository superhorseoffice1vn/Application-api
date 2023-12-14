package com.example.be.service;


import com.example.be.model.account.Account;

import java.util.Optional;

public interface IAccountService {

    Account findAccountByUsername(String username);

    Account createAccount(Account account);

    Account findById(Integer id);

    Account updateAccount(Account account);

    Boolean existsByUsername(String username);

    Optional<Account> findByName(String username);

    void updatePassword(String password, String username);
}
