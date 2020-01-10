package com.yvant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yvant.common.security.util.JwtTokenUtil;
import com.yvant.mapper.admin.AdminLoginLogMapper;
import com.yvant.mapper.admin.AdminMapper;
import com.yvant.mapper.admin.PermissionMapper;
import com.yvant.model.admin.Admin;
import com.yvant.model.admin.AdminLoginLog;
import com.yvant.model.admin.Permission;
import com.yvant.model.admin.bo.AdminUserDetails;
import com.yvant.model.admin.dto.admin.AdminParam;
import com.yvant.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private AdminLoginLogMapper adminLoginLogMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        Admin admin = getAdminByUsername(username);
        if (admin != null) {
            // 根据用户id 获取用户权限
            List<Permission> permissionList = getPermissionList(admin.getId());
            return new AdminUserDetails(admin,permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public List<Permission> getPermissionList(Long adminId) { ;
        return permissionMapper.getPermissionList(adminId);
    }

    @Override
    public Admin register(AdminParam adminParam) {
        // 查询是否有相同的用户名
        LambdaQueryWrapper<Admin> query = new LambdaQueryWrapper<>();
        query.eq(Admin::getUsername, adminParam.getUsername());
        List<Admin> adminList = this.baseMapper.selectList(query);
        if(adminList.size() > 0 ) {
            return null;
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminParam, admin);
        admin.setStatus(1);
        // 密码加密
        String encodePassword = passwordEncoder.encode(adminParam.getPassword());
        admin.setPassword(encodePassword);
        admin.setRealPassword(adminParam.getPassword());
        this.baseMapper.insert(admin);
        return admin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(username);
        }catch (AuthenticationException e) {
            log.error("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }



    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        Admin admin = getAdminByUsername(username);
        AdminLoginLog loginLog = new AdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(LocalDateTime.now());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        adminLoginLogMapper.insert(loginLog);
    }


    @Override
    public Admin getAdminByUsername(String username) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<Admin> adminList = this.baseMapper.selectList(queryWrapper);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }
}
