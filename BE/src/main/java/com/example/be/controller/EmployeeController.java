package com.example.be.controller;

import com.example.be.dto.response.employee.EmployeeDetailDto;
import com.example.be.dto.response.employee.IEmployeeDto;
import com.example.be.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private IUserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<Page<IEmployeeDto>> findAllEmployee(@PageableDefault(value = 5) Pageable pageable) {
        Page<IEmployeeDto> employeeDtos = userService.findAllEmployee(pageable);
        if (employeeDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<EmployeeDetailDto> detail(@PathVariable Integer id) {
        EmployeeDetailDto employeeDetailDto = userService.getUserById(id);
        return new ResponseEntity<>(employeeDetailDto, HttpStatus.OK);
    }
}
