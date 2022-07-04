package com.qinweizhao.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author qinweizhao
 * @since 2022/7/4
 */
@Data
@Entity
@Table(name = "account_details")
public class AccountDetail {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//设置一个自增主键
    @Id
    int id;

    @Column(name = "address")
    String address;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phone;

    @Column(name = "real_name")
    String realName;
}
