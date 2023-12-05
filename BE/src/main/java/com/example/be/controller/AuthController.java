package com.example.be.controller;

import com.example.be.dto.request.SignInForm;
import com.example.be.dto.request.SignUpForm;
import com.example.be.dto.response.JwtResponse;
import com.example.be.dto.response.ResponseMessage;
import com.example.be.model.account.Account;
import com.example.be.model.user.User;
import com.example.be.model.user.UserType;
import com.example.be.security.jwt.JwtProvider;
import com.example.be.security.user.MyUserDetail;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (accountService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("The username existed! Please try again!"), HttpStatus.OK);
        }

        ResponseMessage message;

        message = UserValidator.validatorUser(signUpForm);

        if (!message.getMessage().equals("")) {
            return new ResponseEntity<>(new ResponseMessage(message.getMessage()), HttpStatus.OK);
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

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Validated @RequestBody SignInForm signInForm) {

        if (!accountService.existsByUsername(signInForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Login unsuccessful!"), HttpStatus.OK);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));

        MyUserDetail myUserDetail = (MyUserDetail) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);

        return new ResponseEntity<>(new JwtResponse(token,
                                                    myUserDetail.getAuthorities(),
                                                    myUserDetail.getAccount(),
                                                    myUserDetail.getAccount().getUser()), HttpStatus.OK);
    }

}
