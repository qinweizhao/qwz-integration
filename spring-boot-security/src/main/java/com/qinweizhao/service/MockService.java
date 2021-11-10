package com.qinweizhao.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author qinweizhao
 * @since 2021/11/10
 */
public interface MockService {

    /**
     * 通过用户名获取 UserDetails
     *
     * @param username username
     * @return UserDetails
     */
    UserDetails getUserDetailsByUsername(String username);
}
