package com.qinweizhao.service.impl;

import com.qinweizhao.entity.MyUserDetails;
import com.qinweizhao.service.MockService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author qinweizhao
 * @since 2021/11/10
 */
@Service
public class MockServiceImpl implements MockService {


    @Override
    public UserDetails getUserDetailsByUsername(String username) {
        if (!username.equals("qwz")) {
            throw new UsernameNotFoundException("用户名输入错误");
        }
        String password = "$2a$10$MgUad/jvA/6sET.wYExsdO09jF3OsaeuwHwjowTlHOJ/8aGBYWxMa";
        String authorities = "ROLE_vip1,ROLE_vip2";
        return new MyUserDetails(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities), true, true, true, true);
    }
}
