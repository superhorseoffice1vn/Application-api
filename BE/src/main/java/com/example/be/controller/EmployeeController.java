package com.example.be.controller;

import com.example.be.dto.request.employee.SearchEmployee;
import com.example.be.dto.request.employee.UpdateEmployeeDto;
import com.example.be.dto.response.ResponseMessage;
import com.example.be.dto.response.employee.ChangePasswordForm;
import com.example.be.dto.response.employee.EmployeeDetailDto;
import com.example.be.dto.response.employee.IEmployeeDto;
import com.example.be.model.account.Account;
import com.example.be.model.user.User;
import com.example.be.service.IAccountService;
import com.example.be.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/list")
    public ResponseEntity<Page<IEmployeeDto>> findAllEmployee(
            @RequestBody SearchEmployee searchEmployee,
            @PageableDefault(value = 5) Pageable pageable) {
        Page<IEmployeeDto> employeeDtos = userService.findAllEmployee(searchEmployee,pageable);
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

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordForm changePasswordForm, BindingResult bindingResult) {
        if (!Objects.equals(changePasswordForm.getNewPassword(), changePasswordForm.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "confirmPassword", "Mật khẩu xác nhận không trùng với mật khẩu mới");
        }
        Account account = accountService.findByName(changePasswordForm.getUsername()).orElse(null);
        assert account != null;
        if (!passwordEncoder.matches(changePasswordForm.getPassword(), account.getPassword())) {
            bindingResult.rejectValue("password", "password", "Bạn đã nhập sai mật khẩu cũ");
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        if (passwordEncoder.matches(changePasswordForm.getPassword(), account.getPassword())) {
            account.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
            accountService.updatePassword(account.getPassword(), account.getUsername());
            return new ResponseEntity<>(new ResponseMessage("Cập nhật mật khẩu thành công"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("Thay đổi mật khẩu thất bại"), HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody @Validated UpdateEmployeeDto updateAgentDto,
                                    BindingResult bindingResult,
                                    @PathVariable Integer id) {

        User user = userService.findById(id);
        Account account = accountService.findById(user.getId());
        String newUsername = updateAgentDto.getUsername();

        if (!account.getUsername().equals(newUsername)) {
            if (accountService.existsByUsername(newUsername)) {
                return new ResponseEntity<>(new ResponseMessage("The username existed! Please try again!"), HttpStatus.OK);
            }
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        BeanUtils.copyProperties(updateAgentDto, account);
        account.setPassword(passwordEncoder.encode(updateAgentDto.getPassword()));
        accountService.updateAccount(account);
        BeanUtils.copyProperties(updateAgentDto, user);
        userService.updateUser(user);
        return new ResponseEntity<>(new ResponseMessage("Edit user success!"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdateEmployeeDto> employeeById(@PathVariable() Integer id) {
        UpdateEmployeeDto updateEmployeeDto = userService.findByIdEmployee(id);
        return new ResponseEntity<>(updateEmployeeDto, HttpStatus.OK);
    }
}
