package com.wenjifeng.interceptor.springbootibatisinterceptor.service;

import com.wenjifeng.interceptor.springbootibatisinterceptor.demo.EduEdmoPmsUser;

import java.util.List;

/**
 * @Description TODO
 * @className EduEdmoPmsUserService
 * @Author wen_jf@suixingpay
 * @Date 2019/6/26 14:50
 * @Version 1.0
 **/
public interface EduEdmoPmsUserService {
    public int insert(EduEdmoPmsUser eduEdmoPmsUser) ;
    public EduEdmoPmsUser queryById(String id);
    public List<EduEdmoPmsUser> queryByMainUserId(String mainUserId);
    public Integer queryUserStatus (String id);
}
