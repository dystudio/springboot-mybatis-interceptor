package com.wenjifeng.interceptor.springbootibatisinterceptor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wenjifeng.interceptor.springbootibatisinterceptor.demo.EduEdmoPmsUser;
import com.wenjifeng.interceptor.springbootibatisinterceptor.form.EduEdmoPmsUserForm;
import com.wenjifeng.interceptor.springbootibatisinterceptor.response.ResponseResult;
import com.wenjifeng.interceptor.springbootibatisinterceptor.service.EduEdmoPmsUserService;
import com.wenjifeng.interceptor.springbootibatisinterceptor.util.UUIdUtils;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @className EduEdmoPmsUserController
 * @Author wen_jf@suixingpay
 * @Date 2019/6/26 14:46
 * @Version 1.0
 **/
@RestController
@RequestMapping("/edu")
@Slf4j
public class EduEdmoPmsUserController extends BaseController {

    @Autowired
    private EduEdmoPmsUserService eduEdmoPmsUserService;

    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody EduEdmoPmsUserForm eduEdmoPmsUserForm) {
        EduEdmoPmsUser user = new EduEdmoPmsUser();
        BeanUtils.copyProperties(eduEdmoPmsUserForm, user);

        user.setId(UUIdUtils.get32UUId());
        user.setVersion(1);
        user.setCreateDate(new Date());
        user.setStatus(100);
        user.setLastLoginTime(new Date());
        user.setMainUserId("1");
        log.info("新增开始");
        int result = this.eduEdmoPmsUserService.insert(user);
        if (1 == result) {
            return isOk();
        }
        return isFailure();
    }

    @GetMapping("/queryById")
    public ResponseResult queryById(String id) {
        EduEdmoPmsUser pmsUser = this.eduEdmoPmsUserService.queryById(id);
        return isSuccess(pmsUser);
    }

    @GetMapping("/queryByMainUserId")
    public ResponseResult queryByMainUserId(String mainUserId) {
        List<EduEdmoPmsUser> queryParity = this.eduEdmoPmsUserService.queryByMainUserId(mainUserId);
        return isSuccess(queryParity);
    }

    @GetMapping("/queryUserStatus")
    public ResponseResult queryUserStatus(String id) {
        Integer status = this.eduEdmoPmsUserService.queryUserStatus(id);
        return isSuccess(status);
    }

    @GetMapping("/queryByPage")
    public ResponseResult queryByPage(@RequestParam String mainUserId,@RequestParam int pageNum,
                                      @RequestParam int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        // 调用顺序
        // 1：先调用com.github.pagehelper.PageInterceptor 拦截器，获取查询sql的id和查询sql 例如：获取xml中的queryByMainUserId
        // 2：组装查询总数量sql的idqueryByMainUserId_COUNT，如果在xml中定义该实现，则使用自定义实现；若没有，则组装一个查询总数量sql
        // 3: 根据组装sql查询总数量
        // 4：根据查询sql，构建分页sql(在sql后面加LIMIT ?, ?)
        // 5：执行分页查询sql，并返回查询结果
        List<EduEdmoPmsUser> queryParity = this.eduEdmoPmsUserService.queryByMainUserId(mainUserId);
        PageInfo<EduEdmoPmsUser> pageList = new PageInfo<>(queryParity);
        return isSuccess(pageList);
    }

}
