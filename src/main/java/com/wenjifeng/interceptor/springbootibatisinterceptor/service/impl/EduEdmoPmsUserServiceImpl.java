package com.wenjifeng.interceptor.springbootibatisinterceptor.service.impl;

import com.wenjifeng.interceptor.springbootibatisinterceptor.demo.EduEdmoPmsUser;
import com.wenjifeng.interceptor.springbootibatisinterceptor.mapper.EduEdmoPmsUserMapper;
import com.wenjifeng.interceptor.springbootibatisinterceptor.service.EduEdmoPmsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @className EduEdmoPmsUserServiceImpl
 * @Author wen_jf@suixingpay
 * @Date 2019/6/26 14:51
 * @Version 1.0
 **/
@Service
@Slf4j
public class EduEdmoPmsUserServiceImpl implements EduEdmoPmsUserService{
    @Autowired
    private EduEdmoPmsUserMapper eduEdmoPmsUserMapper;
    @Override
    public int insert(EduEdmoPmsUser eduEdmoPmsUser) {
        return eduEdmoPmsUserMapper.insert(eduEdmoPmsUser);
    }

    @Override
    public EduEdmoPmsUser queryById(String id) {
        return eduEdmoPmsUserMapper.queryById(id);
    }

    @Override
    public List<EduEdmoPmsUser> queryByMainUserId(String mainUserId) {
        return this.eduEdmoPmsUserMapper.queryByMainUserId(mainUserId);
    }

    @Override
    public Integer queryUserStatus(String id) {
        return this.eduEdmoPmsUserMapper.queryUserStatus(id);
    }
}
