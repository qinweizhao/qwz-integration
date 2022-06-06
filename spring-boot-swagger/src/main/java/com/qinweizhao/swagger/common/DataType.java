package com.qinweizhao.swagger.common;

/**
 * 方便在 @ApiImplicitParam 的 dataType 属性使用
 *
 * @author qinweizhao
 * @since 2021/10/12
 */
public final class DataType {

     private DataType() {
     }

     public static final String STRING = "String";
     public static final String INT = "int";
     public static final String LONG = "long";
     public static final String DOUBLE = "double";
     public static final String FLOAT = "float";
     public static final String BYTE = "byte";
     public static final String BOOLEAN = "boolean";
     public static final String ARRAY = "array";
     public static final String BINARY = "binary";
     public static final String DATETIME = "dateTime";
     public static final String PASSWORD = "password";

}
