package com.yvant.model.admin;


import com.yvant.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 后台用户角色表 实体类
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity<Long> {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;


    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;


    /**
     * 后台用户数量
     */
    @ApiModelProperty(value = "后台用户数量")
    private Integer adminCount;


    /**
     * 启用状态：0->禁用；1->启用
     */
    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;


    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;


}
