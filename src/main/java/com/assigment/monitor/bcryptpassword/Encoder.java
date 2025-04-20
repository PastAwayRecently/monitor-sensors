package com.assigment.monitor.bcryptpassword;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder {

    public static void main(String[] args) {
        String yourPassword = "Write your password here";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(yourPassword);
        System.out.println("Encrypted  password -> " + encodedPassword);
        System.out.println("Save this password into table users.");
        boolean matches = encoder.matches(yourPassword, encodedPassword);
        System.out.println(matches);
    }

}
