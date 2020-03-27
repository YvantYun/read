package com.yvant.service.admin.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yvant.mapper.admin.ResourceCategoryMapper;
import com.yvant.model.admin.ResourceCategory;
import com.yvant.service.admin.IResourceCategoryService;
import org.springframework.stereotype.Service;

/**
 * 资源分类表 Service接口实现类
 *
 * @author yunfeng
 * @Description Created on 2020-03-26
 */
@Service
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryMapper, ResourceCategory> implements IResourceCategoryService {

}

