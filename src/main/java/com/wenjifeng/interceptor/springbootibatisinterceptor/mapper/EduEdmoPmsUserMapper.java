package com.wenjifeng.interceptor.springbootibatisinterceptor.mapper;


import com.wenjifeng.interceptor.springbootibatisinterceptor.demo.EduEdmoPmsUser;

import java.util.List;

/**
 * @Description TODO
 * @className EduEdmoPmsUserMapper
 * @Author wen_jf@suixingpay
 * @Date 2019/1/19 14:49
 * @Version 1.0
 **/
public interface EduEdmoPmsUserMapper {
    public int insert(EduEdmoPmsUser eduEdmoPmsUser) ;

    public EduEdmoPmsUser queryById(String id);

    public List<EduEdmoPmsUser> queryByMainUserId(String mainUserId);

    public Integer queryUserStatus (String id);
}
