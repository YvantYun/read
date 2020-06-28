package com.yvant.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yvant.common.CommonPage;
import com.yvant.common.CommonResult;
import com.yvant.model.admin.bo.MenuNode;
import com.yvant.model.admin.entity.Menu;
import com.yvant.service.admin.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2020-03-26
 */

@Api(value = "MenuController", tags = "后台菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "分页查询菜单列表")
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<Menu>> list(@PathVariable Long parentId,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<Menu> menuList = menuService.getMenuList(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(menuList));
    }

    @ApiOperation(value = "添加菜单")
    @PostMapping("/create")
    public CommonResult create(@RequestBody Menu menu) {

        int count = this.menuService.insertMenu(menu);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据ID获取菜单详情")
    @GetMapping("/{id}")
    public CommonResult<Menu> getItem(@PathVariable Long id) {
        Menu menu = menuService.getById(id);
        return CommonResult.success(menu);
    }

    @ApiOperation("根据ID更新菜单")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody Menu menu) {
        int count = menuService.updateMenu(id, menu);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据ID删除菜单")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        boolean result = menuService.removeById(id);
        if (result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @ApiOperation("返回所有树状菜单")
    @GetMapping("/treeList")
    public CommonResult treeList() {
        List<MenuNode> menuNodeList = menuService.treeList();
        return CommonResult.success(menuNodeList);
    }

    @ApiOperation("修改菜单显示状态")
    @PostMapping("/updateHidden/{id}")
    public CommonResult updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden) {
        int count = menuService.updateHidden(id, hidden);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
