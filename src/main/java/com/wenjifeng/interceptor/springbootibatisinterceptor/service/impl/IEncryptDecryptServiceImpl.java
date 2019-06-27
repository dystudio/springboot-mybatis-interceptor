package com.wenjifeng.interceptor.springbootibatisinterceptor.service.impl;

import com.wenjifeng.interceptor.springbootibatisinterceptor.annotations.EncryptDecryptClass;
import com.wenjifeng.interceptor.springbootibatisinterceptor.annotations.EncryptDecryptField;
import com.wenjifeng.interceptor.springbootibatisinterceptor.service.IEncryptDecryptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description 加解密实现
 * @className IEncryptDecryptServiceImpl
 * @Author wen_jf@suixingpay
 * @Date 2019/6/26 16:15
 * @Version 1.0
 **/
@Slf4j
@Service
public class IEncryptDecryptServiceImpl implements IEncryptDecryptService {

    @Override
    public <T> T encrypt(Field[] declaredFields, T parameterObject) throws IllegalAccessException {

        if (null == declaredFields || declaredFields.length <= 0) {
            return parameterObject;
        }
        // 存放加密key值
        Map<String,String> encryptKey = new HashMap<>();
        for (Field field : declaredFields) {
            EncryptDecryptField encryptDecryptField = field.getAnnotation(EncryptDecryptField.class);
            // 设置允许访问私有方法权限
            field.setAccessible(true);
            // 加密存放map
            Map<String,Object> map = new HashMap<>();
            if (Objects.nonNull(encryptDecryptField)) {
                log.info("进入encryptDecryptField不为空");
                Object fieldValue = field.get(parameterObject);
                log.info("fieldValue = {}", fieldValue);

                // 获取加密类型
                String operationType = encryptDecryptField.operationType();
                // 获取加密之后存放密文key的字段
                String encryptField = encryptDecryptField.encryptField();
                // 此处模拟加密
                map = encrypt(fieldValue,operationType);
                String mask = (String) map.get("mask");
                String ciphertext = (String) map.get("ciphertext");
                // 密码加密
                if ("3".equals(operationType)) {
                    field.set(parameterObject,ciphertext);
                } else {
                    field.set(parameterObject,mask);
                    encryptKey.put(encryptField,ciphertext);
                }

            }
        }
        setencryptKeyValue(declaredFields,encryptKey,parameterObject);

        return parameterObject;
    }

    private <T> void setencryptKeyValue(Field[] declaredFields, Map<String, String> encryptKey,T parameterObject) throws IllegalAccessException {
        if (null == declaredFields || null == encryptKey) {
            return;
        }
        for (Field field : declaredFields) {
            String fieldId = field.getName();
            if (encryptKey.containsKey(fieldId)) {
                String value = encryptKey.get(fieldId);
                field.setAccessible(true);
                field.set(parameterObject,value);
            }
        }
    }

    // 有的公司会有一个独立加密服务，调用加密服务，会给调用方返回一个掩码和加密key
    // 掩码存数据库，页面显示掩码159****8980
    // 加密key存数据库，解密的时候，用加密key查询对应明文
    private Map<String,Object> encrypt(Object fieldValue, String encryptType) {
        // @TODO 此处模拟加密业务逻辑处理
        // @TODO 如果加密的是密码，则没有对应的掩码，只有密文key；原有密码字段就是存放密文key的字段
        Map<String,Object> map = new HashMap<>();
        map.put("mask","159****8980");// 加密之后掩码字符串
        map.put("ciphertext","3003a6ae4db16f5a88b0f971beb3527a");// 加密之后的密文key
        return map;
    }

    @Override
    public <T> T decrypt(T result) throws IllegalAccessException {
        if (Objects.isNull(result)) {
            return result;
        }
        Class<?> resultClass = result.getClass();
        Field[] fields = resultClass.getDeclaredFields();
        for (Field field : fields) {
            EncryptDecryptField encryptDecryptField = field.getAnnotation(EncryptDecryptField.class);
            if (Objects.nonNull(encryptDecryptField) && encryptDecryptField.resultDecrypt()) {
                String operationType = encryptDecryptField.operationType();
                field.setAccessible(true);
                Object fieldValue = field.get(result);
                String mask = decryptMethod(operationType,fieldValue);
                field.set(result,mask);
            }
        }
        return result;
    }

    // 此处模拟解密操作
    private String decryptMethod(String operationType, Object fieldValue) {
        // @TODO 此处模拟解密具体业务逻辑
        return "解密之后的内容";
    }
}
