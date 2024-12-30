package ru.netology.authorizationService.repository;

import ru.netology.authorizationService.service.Authorities;
import ru.netology.authorizationService.user.User;

import java.util.*;

public interface UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password);

    public User save(User user);

    public List<User> getAllUsers();

    public Optional<User> getByName(String name);

}
