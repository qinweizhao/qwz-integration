package com.qinweizhao.dubbo.api.vo;


import java.io.Serializable;

/**
 * @author qinweizhao
 * @since 2021/9/27
 */
public class ResultVO<T> implements Serializable {

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 私有化构造器
     **/
    private ResultVO() {
    }

    private ResultVO(ResultVO<T> resultVO) {
        this.code = resultVO.code;
        this.message = resultVO.message;
        this.data = resultVO.data;
    }

    /**
     * Build
     */
    public static class Builder<T> {
        private ResultVO<T> resultVO;

        public ResultVO<T> getResultVO() {
            return resultVO;
        }

        public void setResultVO(ResultVO<T> resultVO) {
            this.resultVO = resultVO;
        }

        public Builder() {
            resultVO = new ResultVO<>();
        }

        public Builder code(int code) {
            resultVO.code = code;
            return this;
        }

        public Builder message(String message) {
            resultVO.message = message;
            return this;
        }

        public Builder data(T data) {
            resultVO.data = data;
            return this;
        }

        public ResultVO<T> build() {
            return new ResultVO<>(resultVO);
        }
    }
}