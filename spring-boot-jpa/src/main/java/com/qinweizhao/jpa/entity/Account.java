package com.qinweizhao.jpa.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * @author qinweizhao
 * @since 2022/7/4
 */
@Data
@Entity   //表示这个类是一个实体类
@Table(name = "account")    //对应的数据库中表名称
public class Account {

    /**
     * 生成策略，这里配置为自增
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    //对应表中id这一列
    @Id     //此属性为主键
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    //一对一
    @JoinColumn(name = "detail_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //设置关联操作为ALL
    private AccountDetail detail;//对象类型,也可以理解这里写哪个实体类,外键就指向哪个实体类的主键

}

