package com.qinweizhao.oauth2.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author qinweizhao
 * @since 2022-06-07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Result {
    private Integer code;

    private String msg;

    private Object data;
}
