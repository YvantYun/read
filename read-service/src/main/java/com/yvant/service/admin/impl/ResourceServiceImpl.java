package com.yvant.service.admin.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yvant.mapper.admin.ResourceMapper;
import com.yvant.model.admin.Resource;
import com.yvant.service.admin.IResourceService;
import org.springframework.stereotype.Service;

/**
 * 后台资源表 Service接口实现类
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

}