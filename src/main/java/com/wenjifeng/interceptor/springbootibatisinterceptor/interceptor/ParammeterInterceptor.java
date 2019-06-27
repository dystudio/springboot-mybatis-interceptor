package com.wenjifeng.interceptor.springbootibatisinterceptor.interceptor;

import com.wenjifeng.interceptor.springbootibatisinterceptor.annotations.EncryptDecryptClass;
import com.wenjifeng.interceptor.springbootibatisinterceptor.service.IEncryptDecryptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Properties;

/**
 * @Description mybatis请求操作拦截器
 * @className ParammeterInterceptor
 * @Author wen_jf@suixingpay
 * @Date 2019/6/26 15:50
 * @Version 1.0
 **/


@Intercepts({@Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class),})
@ConditionalOnProperty(value = "wenjifeng.parammeterInterceptor.enabled", havingValue = "true")
@Component
@Slf4j
public class ParammeterInterceptor implements Interceptor{

    @Autowired
    private IEncryptDecryptService encryptDecryptService;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("+++++++++++++++++++++++++++++进入加密拦截器+++++++++++++++++++++++++++++");
        //拦截 ParameterHandler 的 setParameters 方法 动态设置参数
        if (invocation.getTarget() instanceof ParameterHandler) {
            ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
            PreparedStatement ps = (PreparedStatement) invocation.getArgs()[0];
            // 反射获取 参数对像
            Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
            parameterField.setAccessible(true);
            Object parameterObject = parameterField.get(parameterHandler);
            if (Objects.nonNull(parameterObject)){
                Class<?> parameterObjectClass = parameterObject.getClass();
                EncryptDecryptClass encryptDecryptClass = AnnotationUtils.findAnnotation(parameterObjectClass, EncryptDecryptClass.class);
                if (Objects.nonNull(encryptDecryptClass)){
                    Field[] declaredFields = parameterObjectClass.getDeclaredFields();
                    final Object encrypt = encryptDecryptService.encrypt(declaredFields, parameterObject);
                    log.info("加密处理完毕之后对象：{}",encrypt);
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
