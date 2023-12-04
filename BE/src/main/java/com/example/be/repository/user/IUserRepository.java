package com.example.be.repository.user;

import com.example.be.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository
        extends JpaRepository<User,Integer> {
    Boolean existsByEmail (String email);
}
