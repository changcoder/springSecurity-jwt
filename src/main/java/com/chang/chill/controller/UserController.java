package com.chang.chill.controller;

import com.chang.chill.common.CommonResult;
import com.chang.chill.dto.UserLoginParam;
import com.chang.chill.model.ChillPermission;
import com.chang.chill.model.ChillUser;
import com.chang.chill.service.ChillUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理
 * Created by ChangHeXiang on 2019/7/24.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private ChillUserService chillUserService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 用户注册
     * @param chillUserParam
     * @param result
     * @return
     */
    @PostMapping("/register")
    public CommonResult<ChillUser> register(@RequestBody ChillUser chillUserParam, BindingResult result) {
        ChillUser chillUser = chillUserService.register(chillUserParam);
        if (chillUser == null) {
            CommonResult.failed();
        }
        return CommonResult.success(chillUser);
    }

    /**
     * 用户登陆 返回token
     * @param userLoginParam
     * @param result
     * @return
     */
    @PostMapping("/login")
    public CommonResult login(@RequestBody UserLoginParam userLoginParam, BindingResult result) {
        String token = chillUserService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    /**
     * 获取用户所有权限（包括+-权限）
     * @param userId
     * @return
     */
    @GetMapping("/permission/{userId}")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<List<ChillPermission>> getPermissionList(@PathVariable Long userId) {
        List<ChillPermission> permissionList = chillUserService.getPermissionList(userId);
        return CommonResult.success(permissionList);
    }
}
