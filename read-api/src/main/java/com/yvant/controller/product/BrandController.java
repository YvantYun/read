package com.yvant.controller.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yvant.common.CommonPage;
import com.yvant.common.CommonResult;
import com.yvant.model.product.bo.BrandBO;
import com.yvant.model.product.entity.Brand;
import com.yvant.service.product.IBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2020-03-27
 */

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    @ApiOperation(value = "获取全部品牌列表")
    @GetMapping("/listAll")
    @ResponseBody
    public CommonResult<List<Brand>> getList() {
        List<Brand> brandList = brandService.list();
        return CommonResult.success(brandList);
    }

    @ApiOperation(value = "添加品牌")
    @PostMapping("/create")
    @ResponseBody
    public CommonResult create(@Validated @RequestBody BrandBO brandBO, BindingResult result) {
        int count = brandService.createBrand(brandBO);
        if(count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "更新品牌")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id") Long id,
                               @Validated @RequestBody BrandBO brandBO,
                               BindingResult result) {
        int count = brandService.updateBrand(id, brandBO);
        if (count == 1) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "删除品牌")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        boolean result = brandService.removeById(id);
        if (result) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<Brand>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        IPage<Brand> brandList = brandService.listBrand(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @GetMapping("/{id}")
    public CommonResult getItem(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.getById(id));
    }


}
