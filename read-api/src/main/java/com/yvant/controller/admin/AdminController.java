package com.yvant.controller.admin;

import com.yvant.common.CommonResult;
import com.yvant.common.security.util.JwtTokenUtil;
import com.yvant.model.admin.Admin;
import com.yvant.model.admin.Permission;
import com.yvant.model.admin.Role;
import com.yvant.model.admin.dto.admin.AdminParam;
import com.yvant.model.admin.dto.admin.PermissionNode;
import com.yvant.service.IAdminService;
import com.yvant.service.IPermissionService;
import com.yvant.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IPermissionService permissionService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(@RequestBody @Valid AdminParam adminParam){
        String username = adminParam.getUsername();
        String password = adminParam.getPassword();
        String token = adminService.login(username, password);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", "Bearer");
        return CommonResult.success(tokenMap);
    }
    @ApiOperation(value = "用户刷新token")
    @PostMapping("/refreshToken")
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String refreshToken = adminService.refreshToken(token);
        if(refreshToken == null) {
            return CommonResult.unauthorized("token已过期");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", "Bearer");
        return CommonResult.success(tokenMap);
    }


    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    @ResponseBody
    public CommonResult<Admin> register(@RequestBody @Valid AdminParam adminParam){
        Admin admin = adminService.register(adminParam);
        if(admin == null) {
            return CommonResult.failed("用户注册失败");
        }
        return CommonResult.success(admin);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/info")
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        String username = principal.getName();
        // 查询用户信息
        Admin admin = adminService.getAdminByUsername(username);
        // 查询用户路由信息
        List<Permission> permissionList;
        if(admin.getUsername().equals("admin")) {
            permissionList = permissionService.getAllPermissionList();
        }else {
            permissionList = permissionService.getPermissionList(admin.getId());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("username", admin.getUsername());
        data.put("menus", permissionList);
        data.put("icon", admin.getIcon());
        return CommonResult.success(data);
    }

    @ApiOperation(value = "登出功能")
    @PostMapping("/logout")
    @ResponseBody
    public CommonResult logout(HttpServletRequest request){
        return CommonResult.success(null);
    }



}
