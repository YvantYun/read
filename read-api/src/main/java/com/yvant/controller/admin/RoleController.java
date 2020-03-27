package com.yvant.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.yvant.common.CommonPage;
import com.yvant.common.CommonResult;
import com.yvant.model.admin.Resource;
import com.yvant.model.admin.Role;
import com.yvant.model.admin.entity.Menu;
import com.yvant.service.admin.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2020-03-25
 */

@Api(value = "RoleController", tags = "后台角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @ApiOperation("获取所有角色")
    @GetMapping(value = "/listAll")
    public CommonResult<List<Role>> listAll() {
        List<Role> roleList = roleService.list();
        return CommonResult.success(roleList);
    }

    @ApiOperation("获取角色列表——分页")
    @GetMapping("/list")
    public CommonResult<CommonPage<Role>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<Role> page = roleService.getRoleList(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("添加角色信息")
    @PostMapping("/create")
    public CommonResult create(@RequestBody Role role) {
        boolean save = roleService.save(role);
        if(save) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败");
    }

    @ApiOperation("修改角色")
    @PostMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody Role role) {
        int count = roleService.updateRole(id, role);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除角色")
    @PostMapping(value = "/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = roleService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("更新菜单状态")
    @PostMapping("updateStatus/{id}")
    public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status){
        Role role = new Role();
        role.setId(id);
        role.setStatus(status);
        boolean result = roleService.updateById(role);
        if(result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据roleId获取菜单")
    @GetMapping("/listMenu/{roleId}")
    public CommonResult getMenuList(@PathVariable Long roleId) {
        List<Menu> menuList = roleService.getMenuListByRoleId(roleId);
        return CommonResult.success(menuList);
    }

    @ApiOperation("给角色分配菜单")
    @PostMapping("/allocMenu")
    public CommonResult allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        int count = roleService.allocMenu(roleId, menuIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("给角色分配后端资源")
    @PostMapping("/allocResource")
    public CommonResult allocResource(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        int count = roleService.allocResource(roleId, resourceIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取角色相关资源")
    @GetMapping("/listResource/{roleId}")
    public CommonResult<List<Resource>> listResource(@PathVariable Long roleId) {
        List<Resource> roleList = roleService.listResource(roleId);
        return CommonResult.success(roleList);
    }


}
