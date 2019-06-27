package com.wenjifeng.interceptor.springbootibatisinterceptor.demo;

import com.wenjifeng.interceptor.springbootibatisinterceptor.annotations.EncryptDecryptClass;
import com.wenjifeng.interceptor.springbootibatisinterceptor.annotations.EncryptDecryptField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 实体类
 * @className EduEdmoPmsUserService
 * @Author wen_jf@suixingpay
 * @Date 2019/1/19 14:44
 * @Version 1.0
 **/
@Data
@ToString
@NoArgsConstructor
@EncryptDecryptClass(resultDecrypt = true)
public class EduEdmoPmsUser implements Serializable{
    private String id;
    private int version;
    private Date createDate;
    private String userNo;
    private String userType;
    private String mainUserId;
    @EncryptDecryptField(operationType = "3")
    private String userPwd;
    private String userName;
    @EncryptDecryptField(operationType = "1",encryptField = "mobileNoEncryptKey",resultDecrypt = true)
    private String mobileNo;
    private String email;
    private int status;
    private Date lastLoginTime;
    private int isChangedPwd;
    private int pwdErrorCount;
    private Date pwdErrorTime;
    private String remark;
    private String mobileNoEncryptKey;


    private String name;
}
