package com.yvant.controller.admin;

import com.yvant.common.CommonResult;
import com.yvant.model.admin.ResourceCategory;
import com.yvant.service.admin.IResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2020-03-26
 */

@Api(value = "ResourceCategoryController", tags = "后台资源分类管理")
@RestController
@RequestMapping("/resourceCategory")
public class ResourceCategoryController {

    @Autowired
    private IResourceCategoryService resourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @GetMapping("/listAll")
    public CommonResult list() {
        List<ResourceCategory> sortList = resourceCategoryService.list();
                //.stream()
                //.sorted(Comparator.comparing(ResourceCategory::getSort))
                //.collect(Collectors.toList());
        return CommonResult.success(sortList);
    }

    @ApiOperation("新增后台资源分类")
    @PostMapping("/create")
    public CommonResult create(@RequestBody ResourceCategory resourceCategory) {
        boolean result = resourceCategoryService.save(resourceCategory);
        if(result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改后台资源分类")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id,
                               @RequestBody ResourceCategory resourceCategory) {
        resourceCategory.setId(id);
        boolean result = resourceCategoryService.updateById(resourceCategory);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID删除后台资源")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        boolean result = resourceCategoryService.removeById(id);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }




}
