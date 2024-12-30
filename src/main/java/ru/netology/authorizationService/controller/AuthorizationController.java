package ru.netology.authorizationService.controller;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.authorizationService.argumentResolver.UserAnnotation;
import ru.netology.authorizationService.service.Authorities;
import ru.netology.authorizationService.service.AuthorizationService;
import ru.netology.authorizationService.user.User;

import java.util.*;

@Validated
@RestController
@RequestMapping("/api/users")
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@UserAnnotation @Valid User user) {
        return service.getAuthorities(user);
    }

    @PostMapping
    public User add(@RequestBody @Valid User user) {
        return service.save(user);
    }

    @GetMapping("/{name}")
    public User getUser(@PathVariable String name) {
        return service.getUserByUserName(name);
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getAllUsers();
    }
}
