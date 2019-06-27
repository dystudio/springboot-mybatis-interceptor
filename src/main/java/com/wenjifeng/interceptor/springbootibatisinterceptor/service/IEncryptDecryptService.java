package com.wenjifeng.interceptor.springbootibatisinterceptor.service;

import java.lang.reflect.Field;

/**
 * @Description TODO
 * @className IEncryptDecryptService
 * @Author wen_jf@suixingpay
 * @Date 2019/6/26 16:13
 * @Version 1.0
 **/
public interface IEncryptDecryptService {
    /**
     * 加密方法
     * @param declaredFields 反射bean成员变量
     * @param parameterObject Mybatis入参
     * @param <T>
     * @return
     */
    public <T> T encrypt(Field[] declaredFields, T parameterObject) throws IllegalAccessException;
    /**
     * 解密方法
     * @param result Mybatis 返回值，需要判断是否是ArrayList类型
     * @param <T>
     * @return
     */
    public <T> T decrypt(T result) throws IllegalAccessException;
}
