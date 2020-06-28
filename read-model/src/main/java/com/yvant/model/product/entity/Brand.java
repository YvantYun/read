package com.yvant.model.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yvant.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 品牌表 实体类
 *
 * @author yunfeng
 * @Description Created on 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_brand")
public class Brand extends BaseEntity<Long> {

    /**
     * 品牌名称
     */
    private String name;


    /**
     * 首字母
     */
    private String firstLetter;


    /**
     * 排序
     */
    private Integer sort;


    /**
     * 是否为品牌制造商：0->不是；1->是
     */
    private Integer factoryStatus;


    /**
     *
     */
    private Integer showStatus;


    /**
     * 产品数量
     */
    private Integer productCount;


    /**
     * 产品评论数量
     */
    private Integer productCommentCount;


    /**
     * 品牌logo
     */
    private String logo;


    /**
     * 专区大图
     */
    private String bigPic;


    /**
     * 品牌故事
     */
    private String brandStory;


}
