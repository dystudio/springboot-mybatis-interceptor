package com.wenjifeng.interceptor.springbootibatisinterceptor.interceptor;

import com.wenjifeng.interceptor.springbootibatisinterceptor.annotations.EncryptDecryptClass;
import com.wenjifeng.interceptor.springbootibatisinterceptor.service.IEncryptDecryptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

/**
 * @Description mybatis查询结果返回拦截器
 * @className ResultInterceptor
 * @Author wen_jf@suixingpay
 * @Date 2019/6/27 9:44
 * @Version 1.0
 **/
@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = Statement.class),})
@ConditionalOnProperty(value = "wenjifeng.resultInterceptor.enabled", havingValue = "true")
@Component
@Slf4j
public class ResultInterceptor implements Interceptor {

    @Autowired
    private IEncryptDecryptService iEncryptDecryptService;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("+++++++++++++++++++++++++++++进入解密拦截器+++++++++++++++++++++++++++++");
        Object result = invocation.proceed();
        if (Objects.isNull(result)) {
            return result;
        }
        log.info("查询请求返回结果：{}",result);
        if (result instanceof ArrayList) {
            ArrayList arrayList = (ArrayList) result;
            if (!CollectionUtils.isEmpty(arrayList) && needToDecrypt(arrayList.get(0))) {
                for (Object object : arrayList) {
                    Object decrypt = this.iEncryptDecryptService.decrypt(object);
                    log.info("解密之后的报文：{}",decrypt);
                }
            }
        } else {
            if (needToDecrypt(result)) {
                Object decrypt = this.iEncryptDecryptService.decrypt(result);
                log.info("解密之后的报文：{}",decrypt);
            }
        }

        return result;
    }

    private boolean needToDecrypt(Object object) {
        Class<?> objectClass = object.getClass();
        EncryptDecryptClass encryptDecryptClass = AnnotationUtils.findAnnotation(objectClass, EncryptDecryptClass.class);
        if (Objects.nonNull(encryptDecryptClass) && encryptDecryptClass.resultDecrypt()) {
            return true;
        }
        return false;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
