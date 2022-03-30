package com.qinweizhao.freemarker.model;

import lombok.Data;

/**
 * @author qinweizhao
 * @since 2022/3/30
 */
@Data
public class User {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
