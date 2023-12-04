package com.example.be.service;


import com.example.be.model.account.Account;

import java.util.Optional;

public interface IAccountService {

    Account findAccountByUsername(String username);

    Account createAccount(Account account);

    Boolean existsByUsername(String username);

    Optional<Account> findByName(String userName);

    void updatePassword(String password, String userName);
}
