package com.assigment.monitor.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Shmarlouski
 */
public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}