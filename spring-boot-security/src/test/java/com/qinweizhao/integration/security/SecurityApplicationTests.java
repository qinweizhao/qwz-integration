package com.qinweizhao.integration.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SecurityApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void getPassword() {
        String qwz = bCryptPasswordEncoder.encode("qwz");
        System.out.println(qwz);
    }

    @Test
    void getPasswordAdmin() {
        String qwz = bCryptPasswordEncoder.encode("admin");
        System.out.println(qwz);
    }
}
