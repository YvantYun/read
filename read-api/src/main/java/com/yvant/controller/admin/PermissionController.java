package com.yvant.controller.admin;

import com.yvant.common.CommonResult;
import com.yvant.model.admin.Permission;
import com.yvant.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台用户权限表 控制器
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
*/
@RestController
@RequestMapping("/permission")
@Api(value = "PermissionController", tags = "后台权限管理")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @ApiOperation("添加权限")
    @PostMapping("/create")
    @ResponseBody
    public CommonResult create(@RequestBody Permission permission) {
        boolean save = permissionService.save(permission);
        if(save) {
            return CommonResult.success(save);
        }
        return CommonResult.failed();
    }






}
