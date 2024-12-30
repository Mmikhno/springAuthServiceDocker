package ru.netology.authorizationService.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ru.netology.authorizationService.service.Authorities;

public class User {
    @NotBlank(message = "user name cannot be empty")
    private String userName;
    @NotBlank(message = "password cannot be empty")
    @Size(min = 3, max = 10, message = "The password must be between 2 and 10 characters long")
    private String password;
    private Authorities[] authorities;

    public User() {
    }

    public User(String u, String p) {
        this.userName = u;
        this.password = p;
    }

    public User(String u, String p, Authorities[] a) {
        this.userName = u;
        this.password = p;
        this.authorities = a;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authorities[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authorities[] authorities) {
        this.authorities = authorities;
    }
}
