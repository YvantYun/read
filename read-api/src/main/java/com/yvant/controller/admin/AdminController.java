package com.yvant.controller.admin;

import com.yvant.common.CommonResult;
import com.yvant.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private IAdminService adminService;


    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(){
        return null;
    }






}
