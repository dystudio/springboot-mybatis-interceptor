package com.wenjifeng.interceptor.springbootibatisinterceptor.controller;

import com.wenjifeng.interceptor.springbootibatisinterceptor.response.ResponseResult;

import javax.xml.crypto.Data;

/**
 * @Description TODO
 * @className BaseController
 * @Author wen_jf@suixingpay
 * @Date 2019/6/26 14:47
 * @Version 1.0
 **/
public class BaseController<T> {
    public ResponseResult isOk () {
        return new ResponseResult("0000","成功");
    }

    public ResponseResult isFailure() {
        return new ResponseResult("9999","失败");
    }

    public ResponseResult isSuccess(T data) {
        return new ResponseResult("0000","成功", data);
    }

}
