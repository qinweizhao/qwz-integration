package com.qinweizhao;

import com.qinweizhao.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfigCApplicationTests {

    @Autowired
    Person person;

    @Test
    void contextLoads() {
        person.getAge();
        System.out.println("person = " + person.getName());
    }

}
