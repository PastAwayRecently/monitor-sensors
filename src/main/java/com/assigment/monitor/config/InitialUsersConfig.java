package com.assigment.monitor.config;

import com.assigment.monitor.model.Role;
import com.assigment.monitor.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shmarlouski
 */
@Configuration
public class InitialUsersConfig {

    @Bean
    public CommandLineRunner initUsers(UserService userService) {
        return args -> {
            if (userService.userNotExists("admin")) {
                userService.createUser("admin", "adminPass", Role.ADMINISTRATOR);
            }
            if (userService.userNotExists("user")) {
                userService.createUser("user", "userPass", Role.VIEWER);
            }
        };
    }
}