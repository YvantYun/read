package com.yvant.model.admin.dto.admin;

import com.yvant.model.admin.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-26
 */

public class PermissionNode extends Permission {

    @Getter
    @Setter
    private List<PermissionNode> children;
}
