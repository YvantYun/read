package com.yvant.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yvant.common.CommonPage;
import com.yvant.common.CommonResult;
import com.yvant.model.admin.Admin;
import com.yvant.model.admin.Role;
import com.yvant.model.admin.bo.AdminLoginBO;
import com.yvant.model.admin.bo.AdminRegisterBO;
import com.yvant.model.admin.bo.MenuNode;
import com.yvant.model.admin.entity.Menu;
import com.yvant.service.admin.IAdminService;
import com.yvant.service.admin.IMenuService;
import com.yvant.service.admin.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;


    @ApiOperation(value = "用户登录返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody @Valid AdminLoginBO loginBO, BindingResult result) {
        String token = adminService.login(loginBO.getUsername(), loginBO.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "登出功能")
    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation(value = "刷新token")
    @GetMapping("/refreshToken")
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.unauthorized("token已过期");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);

    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult register(@RequestBody AdminRegisterBO registerBO) {
        Admin admin = adminService.register(registerBO);
        if (admin == null) {
            CommonResult.failed();
        } else {
            admin.setPassword(null);
            admin.setRealPassword(null);
        }
        return CommonResult.success(admin);

    }

    @ApiOperation(value = "获取当前登录的用户信息")
    @GetMapping("/info")
    public CommonResult getAdminInfo(Principal principal) {
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUsername(username);
        List<Role> roleList = adminService.getRoleList(admin.getId());
        List<Menu> menuList = roleService.getMenuListByAdminId(admin.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("username", admin.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("menus", menuList);
        data.put("icon", admin.getIcon());
        return CommonResult.success(data);

    }


    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<Admin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<Admin> iPage = adminService.getAdminList(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(iPage));
    }

    @ApiOperation("获取指定用户的角色")
    @GetMapping("/role/{adminId}")
    public CommonResult getRoleList(@PathVariable Long adminId) {

        List<Role> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }

    @ApiOperation("更新指定用户信息")
    @PostMapping("/update/{id}")
    public CommonResult updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        int count = adminService.updateAdmin(id, admin);
        if (count > 0) {
            return CommonResult.success("更新成功");
        }
        return CommonResult.failed("更新失败");
    }

    @ApiOperation("修改账号状态")
    @PostMapping("/updateStatus/{id}")
    public CommonResult updateAdminStatus(@PathVariable Long id, Integer status) {
        Admin admin = new Admin();
        admin.setStatus(status);
        int count = adminService.updateAdmin(id, admin);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分配角色")
    @PostMapping("/role/update")
    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("分配角色失败");
    }


}
