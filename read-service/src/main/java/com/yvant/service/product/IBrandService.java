package com.yvant.service.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yvant.model.product.bo.BrandBO;
import com.yvant.model.product.entity.Brand;

/**
 * 品牌表 Service接口
 *
 * @author yunfeng
 * @Description Created on 2020-03-30
 */
public interface IBrandService extends IService<Brand> {

    /**
     * 创建品牌
     */
    int createBrand(BrandBO brandBO);

    /**
     * 更新品牌
     */
    int updateBrand(Long id, BrandBO brandBO);

    /**
     * 分页查询品牌列表
     */
    IPage<Brand> listBrand(String keyword, Integer pageNum, Integer pageSize);
}