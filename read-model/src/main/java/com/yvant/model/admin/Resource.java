package com.yvant.model.admin;

import com.yvant.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 后台资源表 实体类
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource extends BaseEntity<Long> {

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    private String name;


    /**
     * 资源URL
     */
    @ApiModelProperty(value = "资源URL")
    private String url;


    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;


    /**
     * 资源分类ID
     */
    @ApiModelProperty(value = "资源分类ID")
    private Long categoryId;


}