package com.qinweizhao.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinweizhao
 * @since 2021/10/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户实体", description = "User Entity")
public class User {

    /**
     * 用户 id
     */
    @ApiModelProperty(value = "用户id", required = true)
    private Integer id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄", required = true)
    private Integer age;

}
