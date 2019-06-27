package com.wenjifeng.interceptor.springbootibatisinterceptor.util;

import java.util.UUID;

/**
 * @Description 随机uuid
 * @className UUIdUtils
 * @Author wen_jf@suixingpay
 * @Date 2019/3/20 15:07
 * @Version 1.0
 **/
public class UUIdUtils {

    public static String get32UUId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
