package com.yvant.model.admin;

import com.yvant.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 后台角色资源关系表 实体类
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResourceRelation extends BaseEntity<Long> {

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;


    /**
     * 资源ID
     */
    @ApiModelProperty(value = "资源ID")
    private Long resourceId;


}
