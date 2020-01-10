package com.yvant.model.admin.bo;

import com.yvant.model.admin.Admin;
import com.yvant.model.admin.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户数据封装
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */

public class AdminUserDetails implements UserDetails {

    private Admin admin;

    private List<Permission> permissionList;

    public AdminUserDetails(Admin admin, List<Permission> permissionList) {
        this.admin = admin;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionList.stream()
                // 去除目录
                .filter(permission -> permission.getValue() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return admin.getStatus().equals(1);
    }
}
