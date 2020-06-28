package com.yvant.service.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yvant.model.admin.Resource;

import java.util.List;

/**
 * 后台资源表 Service接口
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
public interface IResourceService extends IService<Resource> {

    /**
     * 获取后台资源列表 —— 分页 + 模糊查询
     *
     * @param categoryId  资源分类id
     * @param nameKeyword
     * @param urlKeyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage<Resource> getResourceList(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);
}