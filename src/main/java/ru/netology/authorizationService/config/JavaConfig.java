package ru.netology.authorizationService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.netology.authorizationService.argumentResolver.HandlerUserArgumentResolver;
import ru.netology.authorizationService.controller.AuthorizationController;
import ru.netology.authorizationService.repository.UserRepository;
import ru.netology.authorizationService.repository.UserRepositoryImpl;
import ru.netology.authorizationService.service.AuthorizationService;
import ru.netology.authorizationService.service.AuthorizationServiceImpl;

import java.util.List;

@Configuration
public class JavaConfig implements WebMvcConfigurer {
    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }

    @Bean
    public AuthorizationService authorizationService(UserRepository repository) {
        return new AuthorizationServiceImpl(repository);
    }

    @Bean
    public AuthorizationController authorizationController(AuthorizationService service) {
        return new AuthorizationController(service);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new HandlerUserArgumentResolver());
    }

}
