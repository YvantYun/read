package com.yvant.model.admin;


import com.baomidou.mybatisplus.annotation.TableName;
import com.yvant.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 后台用户角色表 实体类
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_role")
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
