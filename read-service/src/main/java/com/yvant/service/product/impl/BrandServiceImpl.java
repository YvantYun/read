package com.yvant.service.product.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yvant.mapper.product.BrandMapper;
import com.yvant.model.product.bo.BrandBO;
import com.yvant.model.product.entity.Brand;
import com.yvant.service.product.IBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 品牌表 Service接口实现类
 *
 * @author yunfeng
 * @Description Created on 2020-03-30
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Override
    public int createBrand(BrandBO brandBO) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandBO, brand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(brand.getFirstLetter())) {
            brand.setFirstLetter(brand.getName().substring(0, 1));
        }
        return this.baseMapper.insert(brand);
    }

    @Override
    public int updateBrand(Long id, BrandBO brandBO) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandBO, brand);
        brand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(brand.getFirstLetter())) {
            brand.setFirstLetter(brand.getName().substring(0, 1));
        }
        // TODO 更新品牌时要更新商品中的品牌名称

        return this.baseMapper.updateById(brand);
    }

    @Override
    public IPage<Brand> listBrand(String keyword, Integer pageNum, Integer pageSize) {
        Page<Brand> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Brand> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("sort");
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like("name", keyword);
        }
        return this.baseMapper.selectPage(page, wrapper);
    }

    @Override
    public boolean updateShowStatus(List<Long> ids, Integer showStatus) {
        List<Brand> brandList = ids.stream().map(id -> {
            Brand brand = new Brand();
            brand.setId(id);
            brand.setShowStatus(showStatus);
            return brand;
        }).collect(Collectors.toList());

        return this.updateBatchById(brandList);
    }
}
