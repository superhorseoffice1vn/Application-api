package com.example.be.service.impl;

import com.example.be.dto.request.employee.SearchEmployee;
import com.example.be.dto.request.employee.UpdateEmployeeDto;
import com.example.be.dto.response.employee.EmployeeDetailDto;
import com.example.be.dto.response.employee.IEmployee;
import com.example.be.dto.response.employee.IEmployeeDto;
import com.example.be.model.user.User;
import com.example.be.repository.user.IUserRepository;
import com.example.be.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;


    @Override
    public Page<IEmployeeDto> findAllEmployee(final SearchEmployee searchEmployee, final Pageable pageable) {
        return userRepository.findAllEmployee(searchEmployee,pageable);
    }

    @Override
    public Page<IEmployeeDto> findAllEmployeeRestore(final SearchEmployee searchEmployee, final Pageable pageable) {
        return userRepository.findAllEmployeeRestore(searchEmployee,pageable);
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
    public User updateUser(final User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findById(final Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UpdateEmployeeDto findByIdEmployee(final Integer id) {
        return userRepository.getByIdEmployee(id);
    }

    @Override
    public List<IEmployeeDto> getEmployees() {
        return userRepository.getEmployees();
    }

    @Override
    public List<IEmployee> getEmployee() {
        return userRepository.getEmployee();
    }

    @Override
    public List<IEmployeeDto> getListEmployees(final List<Integer> idList) {
        return userRepository.getListEmployee(idList);
    }

    @Override
    public List<IEmployeeDto> getListEmployeesRestore(final List<Integer> idList) {
        return userRepository.getListEmployeeRestore(idList);
    }


}
