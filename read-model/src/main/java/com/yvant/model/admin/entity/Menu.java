package com.yvant.model.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yvant.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 后台菜单表 实体类
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_menu")
public class Menu extends BaseEntity<Long> {

    /**
     * 父级ID
     */
    private Long parentId;


    /**
     * 菜单名称
     */
    private String title;


    /**
     * 菜单级数
     */
    private Integer level;


    /**
     * 菜单排序
     */
    private Integer sort;


    /**
     * 前端名称
     */
    private String name;

    private String path;
    private String component;
    private String redirect;
    private String activeMenu;
    private Integer breadCrumb;


    /**
     * 前端图标
     */
    private String icon;


    /**
     * 前端隐藏
     */
    private Integer hidden;


}
