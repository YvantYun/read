package com.yvant.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yvant.common.CommonPage;
import com.yvant.common.CommonResult;
import com.yvant.common.security.filter.DynamicSecurityMetadataSource;
import com.yvant.model.admin.Resource;
import com.yvant.service.admin.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * 后台资源管理控制器
 * </p>
 *
 * @author yunfeng
 * @since 2020-03-26
 */

@Api(value = "ResourceController", tags = "后台资源管理")
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("分页模糊查询后台资源")
    @GetMapping("/list")
    public CommonResult<CommonPage<Resource>> list(@RequestParam(required = false) Long categoryId,
                                                   @RequestParam(required = false) String nameKeyword,
                                                   @RequestParam(required = false) String urlKeyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<Resource> resourceList = resourceService.getResourceList(categoryId, nameKeyword,
                urlKeyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(resourceList));
    }

    @ApiOperation("查询所有后台资源")
    @GetMapping("/listAll")
    public CommonResult listAll() {
        List<Resource> resourceList = resourceService.list();
        return CommonResult.success(resourceList);

    }

    @ApiOperation("添加后台资源")
    @PostMapping("/create")
    public CommonResult create(@RequestBody Resource resource) {
        boolean result = resourceService.save(resource);
        dynamicSecurityMetadataSource.clearDataSource();
        // 动态更新后台权限
        //dynamicSecurityMetadataSource.clearDataSource();
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台资源")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id,
                               @RequestBody Resource resource) {
        resource.setId(id);
        boolean result = resourceService.updateById(resource);
        dynamicSecurityMetadataSource.clearDataSource();
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    //@ApiOperation("根据ID获取资源详情")
    //@GetMapping("/{id}")
    //public CommonResult<Resource> getItem(@PathVariable Long id) {
    //    Resource resource = resourceService.getById(id);
    //    return CommonResult.success(resource);
    //}

    @ApiOperation("根据ID删除后台资源")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        boolean result = resourceService.removeById(id);
        dynamicSecurityMetadataSource.clearDataSource();
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }
}
