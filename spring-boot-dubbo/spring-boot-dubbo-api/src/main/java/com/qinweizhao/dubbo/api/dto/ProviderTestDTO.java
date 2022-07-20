package com.qinweizhao.dubbo.api.dto;


import java.io.Serializable;

/**
 * @author qinweizhao
 * @since 2021/9/27
 */
public class ProviderTestDTO implements Serializable {
    // ID
    private Integer id;
    // 名字
    private String name;
    // 序号
    private Integer number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}