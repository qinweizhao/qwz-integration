package com.qinweizhao.integration.security.service.impl;

import com.qinweizhao.integration.security.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @author qinweizhao
 * @since 2021-11-10
 */
@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private MockService mockService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return mockService.getUserDetailsByUsername(username);
    }

}
