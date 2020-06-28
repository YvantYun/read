package com.yvant.service.admin.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yvant.mapper.admin.ResourceMapper;
import com.yvant.model.admin.Resource;
import com.yvant.service.admin.IResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台资源表 Service接口实现类
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Override
    public IPage<Resource> getResourceList(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        Page<Resource> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        if (categoryId != null) {
            queryWrapper.eq("category_id", categoryId);
        }
        if (StrUtil.isNotEmpty(nameKeyword)) {
            queryWrapper.like("name", nameKeyword);
        }
        if (StrUtil.isNotEmpty(urlKeyword)) {
            queryWrapper.like("url", urlKeyword);
        }
        return this.baseMapper.selectPage(page, queryWrapper);
    }
}