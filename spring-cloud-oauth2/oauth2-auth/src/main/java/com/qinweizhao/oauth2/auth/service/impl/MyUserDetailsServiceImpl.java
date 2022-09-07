package com.qinweizhao.oauth2.auth.service.impl;

import com.qinweizhao.oauth2.auth.model.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author qinweizhao
 * @since 2022/6/8
 */
@Slf4j
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    static List<SecurityUser> users = new ArrayList<>();

    static {
        SecurityUser admin = SecurityUser.builder().userId(UUID.randomUUID().toString().replaceAll("-", "")).username("admin").password(new BCryptPasswordEncoder().encode("123456")).authorities(AuthorityUtils.createAuthorityList("ROLE_user", "ROLE_admin")).build();
        SecurityUser user = SecurityUser.builder().userId(UUID.randomUUID().toString().replaceAll("-", "")).username("user").password(new BCryptPasswordEncoder().encode("123456")).authorities(AuthorityUtils.createAuthorityList("ROLE_user")).build();

        users.add(admin);
        users.add(user);
        log.info("用户已经保存"+users.toString());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("从数据库查询用户信息方法执行...");
        System.out.println("从数据库查询用户信息方法执行...");
        //从数据库中查询
        List<SecurityUser> list = users.stream().filter(p -> username.equals(p.getUsername())).limit(1).collect(Collectors.toList());

        //用户不存在直接抛出 UsernameNotFoundException，security 会捕获抛出 BadCredentialsException

        if (Objects.isNull(list.get(0))) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        return list.get(0);
    }

}
