package com.qinweizhao.swagger.common;

/**
 * 方便在 @ApiImplicitParam 的 paramType 属性使用
 *
 * @author qinweizhao
 * @since 2021-10-12
 */
public final class ParamType {

    private ParamType() {
    }

    public static final String QUERY = "query";
    public static final String HEADER = "header";
    public static final String PATH = "path";
    public static final String BODY = "body";
    public static final String FORM = "form";

}
