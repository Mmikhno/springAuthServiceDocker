package ru.netology.authorizationService.repository;

import ru.netology.authorizationService.service.Authorities;
import ru.netology.authorizationService.user.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class UserRepositoryImpl implements UserRepository {
    Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public List<Authorities> getUserAuthorities(String name, String password) {
        User user = users.get(name);
        Authorities[] authorities = null;
        if (user.getPassword().equals(password)) {
            authorities = user.getAuthorities();
            return Arrays.asList(authorities);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return users.entrySet().stream().map(i -> i.getValue()).collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        String name = user.getUserName();
        users.put(name, user);
        return user;
    }

    @Override
    public Optional<User> getByName(String name) {
        return Optional.ofNullable(users.get(name));
    }
}



