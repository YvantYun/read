package com.yvant.common.base;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 通用实体类包装
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-24
 */

@Data
public class BaseEntity<T> implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private T id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除：0->未删除；1->删除
     */
    @ApiModelProperty(value = "逻辑删除")
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
