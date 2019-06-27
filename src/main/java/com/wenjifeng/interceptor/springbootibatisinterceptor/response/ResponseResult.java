package com.wenjifeng.interceptor.springbootibatisinterceptor.response;

import lombok.Data;

/**
 * @Description 响应结果
 * @className ResponseResult
 * @Author wen_jf@suixingpay
 * @Date 2019/6/24 11:07
 * @Version 1.0
 **/
@Data
public class ResponseResult<T> {

    private String code;
    private String message;
    private T data;

    public ResponseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(String code, String message,T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
