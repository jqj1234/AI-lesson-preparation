package com.ai.controller;

import com.ai.entity.User;
import com.ai.result.Result;
import com.ai.service.UserService;
import com.ai.vo.reqvo.LoginReqVo;
import com.ai.vo.respvo.LoginRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ：褚婧雯
 * @date ：2025/3/25 13:49
 * @description ：用户controller
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param loginReqVo
     * @return
     */
    @PostMapping("/login")
    public Result<LoginRespVo> userLogin(@RequestBody LoginReqVo loginReqVo) {
        return userService.login(loginReqVo);
    }


    /**
     * 用户注册
     * @param loginReqVo
     * @return
     */
    @PostMapping("/register")
    public Result userRegister(@RequestBody LoginReqVo loginReqVo){
        return userService.register(loginReqVo);
    }

    /**
     * 登录校验码生成服务方法
     * @return
     */
    @GetMapping("/captcha")
    public Result<Map> getCaptchaCode(){
        return userService.getCaptchaCode();
    }

    @GetMapping("/getAllUser")
    public Result<List<User>> getAllUser(){
        return Result.success(userService.getAllUser());
    }
}
