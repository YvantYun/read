package com.yvant.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yvant.mapper.admin.MenuMapper;
import com.yvant.model.admin.Role;
import com.yvant.model.admin.bo.MenuNode;
import com.yvant.model.admin.entity.Menu;
import com.yvant.service.admin.IMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台菜单表 Service接口实现类
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Override
    public IPage<Menu> getMenuList(Long parentId, Integer pageSize, Integer pageNum) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId).orderByAsc("sort");
        Page<Menu> page = new Page<>(pageNum, pageSize);
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public int insertMenu(Menu menu) {
        setMenuLevel(menu);
        return this.baseMapper.insert(menu);
    }

    @Override
    public int updateMenu(Long id, Menu menu) {
        setMenuLevel(menu);
        menu.setId(id);
        return this.baseMapper.updateById(menu);
    }

    @Override
    public List<MenuNode> treeList() {
        List<Menu> menuList = this.baseMapper.selectList(null);
        List<MenuNode> menuNodeList = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());

        return menuNodeList;

    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setHidden(hidden);
        return this.baseMapper.updateById(menu);
    }


    /**
     * 将menu转成menuList 并设置属性
     *
     * @param menu
     * @param menuList
     * @return
     */
    private MenuNode covertMenuNode(Menu menu, List<Menu> menuList) {
        MenuNode node = new MenuNode();
        BeanUtils.copyProperties(menu, node);
        List<MenuNode> menuNodeList = menuList.stream()
                // 去除一级菜单
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                // 递归获取下一级菜单
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(menuNodeList);
        return node;

    }

    private void setMenuLevel(Menu menu) {
        if (menu.getParentId() == 0) {
            menu.setLevel(0);
        } else {
            menu.setLevel(1);
        }
    }

}
