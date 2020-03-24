package com.yvant.model.admin;

import com.yvant.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 后台用户和角色关系表 实体类
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRoleRelation extends BaseEntity<Long> {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long adminId;


    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Long roleId;


}
