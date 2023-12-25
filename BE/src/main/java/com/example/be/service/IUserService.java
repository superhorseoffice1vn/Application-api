package com.example.be.service;

import com.example.be.dto.request.employee.SearchEmployee;
import com.example.be.dto.request.employee.UpdateEmployeeDto;
import com.example.be.dto.response.Agent.IAgentAdminDto;
import com.example.be.dto.response.employee.EmployeeDetailDto;
import com.example.be.dto.response.employee.IEmployee;
import com.example.be.dto.response.employee.IEmployeeDto;
import com.example.be.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    Page<IEmployeeDto> findAllEmployee(SearchEmployee searchEmployee, Pageable pageable);

    EmployeeDetailDto getUserById(Integer id);

    void createUser(User user);

    User updateUser(User user);

    Boolean existsByEmail ( String email);

    User findById(Integer id);

    UpdateEmployeeDto findByIdEmployee(Integer id);

    List<IEmployeeDto> getEmployees();

    List<IEmployee> getEmployee();

    List<IEmployeeDto> getListEmployees(List<Integer> idList);

}
