package com.yvant.model.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yvant.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台用户登录日志表
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_login_log")
public class AdminLoginLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long adminId;

    private String ip;

    private String address;

    /**
     * 浏览器登录类型
     */
    private String userAgent;


}
