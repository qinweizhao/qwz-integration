package com.qinweizhao.elasticjob.lite.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author qinweizhao
 * @since 2022/10/17
 */
@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String address;
}
