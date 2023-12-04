package com.example.be.controller;

import com.example.be.dto.request.SignUpForm;
import com.example.be.dto.response.ResponseMessage;
import com.example.be.model.account.Account;
import com.example.be.model.user.User;
import com.example.be.model.user.UserType;
import com.example.be.service.IAccountRoleService;
import com.example.be.service.IAccountService;
import com.example.be.service.IUserService;
import com.example.be.service.impl.AccountService;
import com.example.be.service.impl.UserService;
import com.example.be.validators.UserValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.text.ParseException;

@RequestMapping("api/auth")
@RestController
public class AuthController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountRoleService accountRoleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm)
            throws ParseException {
        if (accountService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("The username existed! Please try again!"), HttpStatus.OK);
        }

        ResponseMessage message;

        message = UserValidator.validatorUser(signUpForm);

        if (!message.getMessage().equals("")) {
            return new ResponseEntity<>( new ResponseMessage(message.getMessage()), HttpStatus.OK);
        }

        User user = new User();
        Account account = new Account();
        BeanUtils.copyProperties(signUpForm, account);

        UserType userType = new UserType();
        userType.setId(signUpForm.getIdUserType());
        user.setUserType(userType);

        BeanUtils.copyProperties(signUpForm, userService);
        account.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        Account account1 = accountService.createAccount(account);

        BeanUtils.copyProperties(signUpForm, user);

        user.setAccount(account1);
        user.setDeleteStatus(false);


        userService.createUser(user);
        accountRoleService.createAccountRole(user.getAccount().getId(), 2);
        return new ResponseEntity<>(new ResponseMessage("Create user success!"), HttpStatus.OK);
    }

}
