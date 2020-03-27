package com.yvant.model.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yvant.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_resource_category")
public class ResourceCategory extends BaseEntity<Long> {

    /**
     * 分类名称
     */
    private String name;


    /**
     * 排序
     */
    private Integer sort;


}
