package com.example.be.service.impl;

import com.example.be.model.account.Account;
import com.example.be.repository.account.IAccountRepository;
import com.example.be.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Account findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findById(final Integer id) {
        return accountRepository.findById(id).orElse(null);
    }


    @Override
    public Account updateAccount(final Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public Optional<Account> findByName(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public void updatePassword(String password, String username) {
        accountRepository.changePassword(password,username);
    }
}
