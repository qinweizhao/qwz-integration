package com.qinweizhao.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 *
 *
 *
 *
 * @author qinweizhao
 * @since 2021/9/17
 */
@Component
//@PropertySource(value = "classpath:person.yml",factory = YamlPropertySourceFactory.class)
@PropertySource(value = "classpath:person.properties")
@ConfigurationProperties(prefix = "person")
public class Person {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private Date birth;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 朋友
     */
    private List<Object> friend;

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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Object> getFriend() {
        return friend;
    }

    public void setFriend(List<Object> friend) {
        this.friend = friend;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
