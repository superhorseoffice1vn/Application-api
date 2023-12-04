package com.example.be.service;

import com.example.be.model.user.User;

public interface IUserService {
    User getUserById(Integer id);

    void createUser(User user);

    Boolean existsByEmail ( String email);

}
