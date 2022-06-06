package com.qinweizhao.security.voter;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

/**
 * @author qinweizhao
 * @since 2021/11/10
 */
public class MyExpressionVoter implements AccessDecisionVoter<Object> {


    @Override
    public boolean supports(ConfigAttribute attribute) {
        return attribute.getAttribute() != null && ("IS_AUTHENTICATED_FULLY".equals(attribute.getAttribute()) || "IS_AUTHENTICATED_REMEMBERED".equals(attribute.getAttribute()) || "IS_AUTHENTICATED_ANONYMOUSLY".equals(attribute.getAttribute()));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> attributes) {
        // 弃权
        return 0;
    }
}
