package com.qinweizhao.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 * * prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 *
 * @author qinweizhao
 * @since 2021/9/17
 */
@Component
@ConfigurationProperties(prefix = "person")
@Validated
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
     * 类上标注 @Validated 注解
     * 属性标注 @Email
     */
    @Email
    private String email;


    /**
     * 宠物
     */
    private Map<String, Pet> pet;

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

    public Map<String, Pet> getPet() {
        return pet;
    }

    public void setPet(Map<String, Pet> pet) {
        this.pet = pet;
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
