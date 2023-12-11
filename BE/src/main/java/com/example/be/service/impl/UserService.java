package com.example.be.service.impl;

import com.example.be.dto.response.employee.EmployeeDetailDto;
import com.example.be.dto.response.employee.IEmployeeDto;
import com.example.be.model.user.User;
import com.example.be.repository.user.IUserRepository;
import com.example.be.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Page<IEmployeeDto> findAllEmployee(final Pageable pageable) {
        return userRepository.findAllEmployee(pageable);
    }

    @Override
    public EmployeeDetailDto getUserById(final Integer id) {
        return userRepository.getDetailEmployee(id);
    }


    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findById(final Integer id) {
        return userRepository.findById(id).orElse(null);
    }


}
