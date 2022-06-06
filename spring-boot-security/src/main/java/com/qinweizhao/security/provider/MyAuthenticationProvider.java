package com.qinweizhao.security.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author qinweizhao
 * @since 2021/11/10
 */
public class MyAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
//        String username = "admin";
//        String password = "$2a$10$JcqIgpnfnFClNnyaX1ClYejz92c9EEjHHE61NTKmBep0bZke3wcZO";
//        String authorities = "ROLE_vip1,ROLE_vip2";
//        return new MyUserDetails(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities), true, true, true, true);
        return null;
    }
}
