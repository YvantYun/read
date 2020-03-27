package com.yvant.service.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yvant.model.admin.bo.MenuNode;
import com.yvant.model.admin.entity.Menu;

import java.util.List;

/**
 * 后台菜单表 Service接口
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 获取菜单列表 分页
     * @param parentId
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage<Menu> getMenuList(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 添加菜单菜单
     * @param menu
     * @return
     */
    int insertMenu(Menu menu);

    /**
     *
     * @param id
     * @param menu
     * @return
     */
    int updateMenu(Long id, Menu menu);

    /**
     * 获取树状菜单
     * @return
     */
    List<MenuNode> treeList();

    /**
     * 修改菜单是否显示
     * @param id
     * @param hidden
     * @return
     */
    int updateHidden(Long id, Integer hidden);
}
