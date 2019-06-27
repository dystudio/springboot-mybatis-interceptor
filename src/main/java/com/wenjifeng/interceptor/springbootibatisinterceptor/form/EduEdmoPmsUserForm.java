package com.wenjifeng.interceptor.springbootibatisinterceptor.form;

import lombok.Data;

import java.util.Date;

/**
 * @Description 表单提交
 * @className EduEdmoPmsUserForm
 * @Author wen_jf@suixingpay
 * @Date 2019/6/26 14:49
 * @Version 1.0
 **/
@Data
public class EduEdmoPmsUserForm {
    private String userNo;
    private String userType;
    private String mainUserId;
    private String userPwd;
    private String userName;
    private String mobileNo;
    private String email;
}
