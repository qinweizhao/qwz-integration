package com.qinweizhao;

import com.qinweizhao.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfigBApplicationTests {

    @Autowired
    Person person;

    @Test
    void contextLoads() {
        System.out.println("person = " + person.getName());
        System.out.println("person = " + person.getAge());
    }

}
