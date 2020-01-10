package com.yvant.model.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yvant.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * 后台用户权限表
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_permission")
public class Permission extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 父级权限id
     */
    @ApiModelProperty(value = "父级权限id")
    private Long pid;

    /**
     * 名称
     */
    @ApiModelProperty(value = "权限名称")
    private String name;

    /**
     * 权限值存redis
     */
    @ApiModelProperty(value = "redis存值")
    private String value;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    @ApiModelProperty(value = "0->目录；1->菜单；2->按钮（接口绑定权限）")
    private Integer type;

    /**
     * 前端资源路径
     */
    @ApiModelProperty(value = "前端路由")
    private String uri;

    /**
     * 启用状态；0->禁用；1->启用
     */
    @ApiModelProperty(value = "启用状态；0->禁用；1->启用")
    private Integer status;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;


}
