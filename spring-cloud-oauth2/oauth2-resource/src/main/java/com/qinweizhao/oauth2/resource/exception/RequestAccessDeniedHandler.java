package com.qinweizhao.oauth2.resource.exception;


import com.qinweizhao.oauth2.resource.model.Result;
import com.qinweizhao.oauth2.resource.model.ResultCode;
import com.qinweizhao.oauth2.resource.utils.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 当认证后的用户访问受保护的资源时，权限不够，则会进入这个处理器
 * @author weizhao
 */
@Component
public class RequestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        //TODO 无权限访问 根据业务自己定制提示信息
        ResponseUtils.result(response,new Result(ResultCode.NO_PERMISSION.getCode(),ResultCode.NO_PERMISSION.getMsg(),null));
    }
}