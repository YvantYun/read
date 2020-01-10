package com.yvant.model.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yvant.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台用户角色表
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty(value = "角色描述")
    private String description;

    /**
     * 启用状态：0->禁用；1->启用
     */
    @ApiModelProperty(value = "启用状态")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
