package com.qinweizhao.oauth2.auth.excepion;


import com.qinweizhao.oauth2.auth.model.Result;
import com.qinweizhao.oauth2.auth.model.ResultCode;
import com.qinweizhao.oauth2.auth.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于处理客户端想认证出错，包括客户端id、密码错误
 *
 * @author qinweizhao
 * @since 2022/6/7
 */
@Component
@Slf4j
public class AuthServerAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 认证失败处理器会调用这个方法返回提示信息
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ResponseUtils.result(response, new Result(ResultCode.CLIENT_AUTHENTICATION_FAILED.getCode(), ResultCode.CLIENT_AUTHENTICATION_FAILED.getMsg(), null));
    }

}