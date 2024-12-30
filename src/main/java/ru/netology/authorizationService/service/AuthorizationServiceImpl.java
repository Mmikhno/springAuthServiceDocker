package ru.netology.authorizationService.service;

import ru.netology.authorizationService.exception.InvalidCredentials;
import ru.netology.authorizationService.exception.NotFoundException;
import ru.netology.authorizationService.exception.UnauthorizedUser;
import ru.netology.authorizationService.repository.UserRepository;
import ru.netology.authorizationService.user.User;

import java.util.*;

public class AuthorizationServiceImpl implements AuthorizationService {
    UserRepository userRepository;

    public AuthorizationServiceImpl(UserRepository repository) {
        userRepository = repository;
    }

    public List<Authorities> getAuthorities(User user) {
        userRepository.getByName(user.getUserName())
                .orElseThrow(() -> new InvalidCredentials("User name is incorrect"));
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user.getUserName(), user.getPassword());
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user.getUserName());
        }
        return userAuthorities;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserName(String name) {
        return userRepository.getByName(name).orElseThrow(() -> new NotFoundException(String.format("User %s not found", name)));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
