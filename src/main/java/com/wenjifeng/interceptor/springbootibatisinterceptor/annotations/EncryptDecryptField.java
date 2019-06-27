package com.wenjifeng.interceptor.springbootibatisinterceptor.annotations;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @Description 加密字段上注解
 * @className EncryptDecryptField
 * @Author wen_jf@suixingpay
 * @Date 2019/6/26 15:50
 * @Version 1.0
 **/

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface EncryptDecryptField {
    // 加密/解密类型（1：手机号加密/解密；2：姓名加密/解密；3：密码加密/解密）
    String operationType ();
    // 存放加密串字段
    String encryptField () default "";
    // 对返回结果是否需要解密；默认不需要
    boolean resultDecrypt() default false;
}
