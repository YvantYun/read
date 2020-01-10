package com.yvant.common;

import lombok.Getter;

/**
 * <p>
 * API 返回码
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-22
 */

@Getter
public enum ResultCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
