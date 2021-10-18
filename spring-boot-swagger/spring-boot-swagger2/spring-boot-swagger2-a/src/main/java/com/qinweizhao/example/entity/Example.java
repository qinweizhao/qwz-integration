package com.qinweizhao.example.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author qinweizhao
 * @since 2021/10/13
 */
@ApiModel
public class Example {

    @ApiModelProperty(value = "主键")
    private String exam;


    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }
}
