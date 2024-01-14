package com.example.be.dto.response;

import com.example.be.model.account.Account;
import com.example.be.model.user.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {

    private Integer id;
    private String token;
    private Collection<? extends GrantedAuthority> roles;
    private String type = "Bearer";
    private Account account;
    private User user;

    public JwtResponse() {
    }

    public JwtResponse(String token, Collection<? extends GrantedAuthority> roles,
                      Account account, User user) {
        this.token = token;
        this.roles = roles;
        this.account = account;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(final Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}
