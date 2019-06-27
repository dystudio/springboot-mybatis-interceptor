package com.wenjifeng.interceptor.springbootibatisinterceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.wenjifeng.interceptor.springbootibatisinterceptor.mapper"})
public class SpringbootIbatisInterceptorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootIbatisInterceptorApplication.class, args);
	}

}
