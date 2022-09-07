package com.qinweizhao.oauth2.gateway.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qinweizhao.oauth2.gateway.model.Result;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 结果封装类
 * @author weizhao
 */
public class ResponseUtils {

    public static void result(HttpServletResponse response, Result msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(msg).getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
