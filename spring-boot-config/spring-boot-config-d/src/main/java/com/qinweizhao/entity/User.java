package com.qinweizhao.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

/**
 *
 * @author qinweizhao
 * @since 2021/9/17
 */
@Validated
@Component
@ConfigurationProperties(prefix = "entity.user")
public class User {

    /**
     * 名字
     */
    @Value("${entity.user.name}")
    private String name;

    /**
     * 年龄
     */
    @Value("${entity.user.age}")
    private Integer age;

    /**
     * 邮箱
     */
    @Email
    private String email;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
