package com.yvant.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-31
 */
@Data
public class CommonPage<T> {
    private Long pageNum;
    private Long pageSize;
    private Long totalPage;
    private Long total;
    private List<T> list;

    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(IPage<T> iPage) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(iPage.getPages());
        result.setPageNum(iPage.getCurrent());
        result.setPageSize(iPage.getSize());
        result.setTotal(iPage.getTotal());
        result.setList(iPage.getRecords());
        return result;
    }

}
