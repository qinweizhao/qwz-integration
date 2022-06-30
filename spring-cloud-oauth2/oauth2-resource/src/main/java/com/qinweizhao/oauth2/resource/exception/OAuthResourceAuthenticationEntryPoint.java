package com.qinweizhao.oauth2.resource.exception;


import com.qinweizhao.oauth2.resource.model.Result;
import com.qinweizhao.oauth2.resource.model.ResultCode;
import com.qinweizhao.oauth2.resource.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * TOKEN失效或者校验失败的处理器
 * @author weizhao
 */
@Component
@Slf4j
public class OAuthResourceAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //TODO token失效提示
        ResponseUtils.result(response,new Result(ResultCode.TOKEN_INVALID.getCode(),ResultCode.TOKEN_INVALID.getMsg(),null));
    }
}