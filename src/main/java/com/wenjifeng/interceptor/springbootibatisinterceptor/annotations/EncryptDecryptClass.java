package com.wenjifeng.interceptor.springbootibatisinterceptor.annotations;

import java.lang.annotation.*;

/**
 * @Description 加密类上的注解
 * @className EncryptDecryptClass
 * @Author wen_jf@suixingpay
 * @Date 2019/6/26 15:49
 * @Version 1.0
 **/
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptDecryptClass {
    // 对返回结果是否需要解密；默认不需要
    boolean resultDecrypt() default false;
}
