package com.example.be.service.impl;

import com.example.be.repository.account.IAccountRoleRepository;
import com.example.be.service.IAccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleService implements IAccountRoleService {

    @Autowired
    private IAccountRoleRepository accountRoleRepository;
    @Override
    public void createAccountRole(Integer accountId, Integer roleId) {
        accountRoleRepository.createAccountRole(accountId,roleId);
    }
}
