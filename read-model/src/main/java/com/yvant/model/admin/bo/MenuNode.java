package com.yvant.model.admin.bo;

import com.yvant.model.admin.entity.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2020-03-26
 */

@Getter
@Setter
public class MenuNode extends Menu {

    List<MenuNode> children;
}
