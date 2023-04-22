package com.qinweizhao.integration.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author qinweizhao
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDetails implements UserDetails {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    private List<GrantedAuthority> authorities;

    /**
     * 账户是否过期
     */
    private boolean accountNonExpired;

    /**
     * 账户是否锁定
     */
    private boolean accountNonLocked;

    /**
     * 凭证是否过期
     */
    private boolean credentialsNonExpired;

    /**
     * 是否禁用
     */
    private boolean enabled;


}
