package com.yvant.controller.admin;

import com.yvant.common.CommonResult;
import com.yvant.model.admin.Admin;
import com.yvant.model.admin.bo.AdminLoginBO;
import com.yvant.model.admin.bo.AdminRegisterBO;
import com.yvant.service.admin.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 后台用户表 控制器
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
*/
@RestController
@RequestMapping("/admin")
@Api(value = "AdminController", tags = "后台用户管理")
public class AdminController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private IAdminService adminService;


    @ApiOperation(value = "用户登录返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody @Valid AdminLoginBO loginBO, BindingResult result){
        String token = adminService.login(loginBO.getUsername(), loginBO.getPassword());
        if(token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult register(@RequestBody AdminRegisterBO registerBO){
        Admin admin = adminService.register(registerBO);
        if (admin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(admin);
    }





}
