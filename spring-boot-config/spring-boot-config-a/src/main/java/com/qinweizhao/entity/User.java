package com.qinweizhao.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * * <bean class="User">
 * *      <property name="name" value="字面量/${key}从环境变量、配置文件中获取值/#{SpEL}"></property>
 * *      <property name="age" value="字面量/${key}从环境变量、配置文件中获取值/#{SpEL}"></property>
 * * <bean/>
 *
 * @author qinweizhao
 * @since 2021/9/17
 */
//
@Component
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
