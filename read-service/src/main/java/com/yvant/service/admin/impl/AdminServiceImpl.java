package com.yvant.service.admin.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yvant.common.security.util.JwtTokenUtil;
import com.yvant.mapper.admin.AdminLoginLogMapper;
import com.yvant.mapper.admin.AdminMapper;
import com.yvant.mapper.admin.AdminRoleRelationMapper;
import com.yvant.model.admin.*;
import com.yvant.model.admin.bo.AdminRegisterBO;
import com.yvant.model.admin.bo.AdminUserDetails;
import com.yvant.service.admin.IAdminRoleRelationService;
import com.yvant.service.admin.IAdminService;
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
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AdminRoleRelationMapper adminRoleRelationMapper;

    @Autowired
    private AdminLoginLogMapper adminLoginLogMapper;

    @Autowired
    private IAdminRoleRelationService adminRoleRelationService;

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            // 插入登录记录
            insertLoginLog(username);
        }catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        Admin admin = getAdminByUsername(username);
        if(admin == null)
            return;
        AdminLoginLog loginLog = new AdminLoginLog();
        loginLog.setAdminId(admin.getId());
        // 获取ip
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        adminLoginLogMapper.insert(loginLog);

    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Admin admin = getAdminByUsername(username);
        if(admin != null) {
            List<Resource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
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

    @Override
    public List<Resource> getResourceList(Long adminId) {
        List<Resource> resourceList = adminRoleRelationMapper.getResourceList(adminId);
        // huTool中判断list不为空
        if(CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        return null;
    }

    @Override
    public Admin register(AdminRegisterBO registerBO) {
        //查询是否有相同用户名的用户
        Admin selectAdmin = getAdminByUsername(registerBO.getUsername());
        if(selectAdmin != null ) {
            return null;
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(registerBO, admin);
        admin.setStatus(1);
        admin.setRealPassword(registerBO.getPassword());
        // 密码加密
        String encodePassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodePassword);
        this.baseMapper.insert(admin);
        return admin;

    }

    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

    @Override
    public IPage<Admin> getAdminList(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("username", keyword)
                    .or()
                    .like("nick_name", keyword);
        }
        Page<Admin> page = new Page<>(pageNum, pageSize);
        return this.baseMapper.selectPage(page, queryWrapper);

    }

    @Override
    public List<Role> getRoleList(Long adminId) {
        return adminRoleRelationMapper.getRoleList(adminId);
    }

    @Override
    public int updateAdmin(Long id, Admin admin) {
        admin.setId(id);
        Admin rawAdmin = this.baseMapper.selectById(id);
        if(rawAdmin.getPassword().equals(admin.getPassword())) {
            // 密码相同则不改
            admin.setPassword(null);
        }else {
            //与原加密密码不同的需要加密修改
            if(StringUtils.isEmpty(admin.getPassword())) {
                admin.setPassword(null);
            }else {
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        return this.baseMapper.updateById(admin);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        // 删除原来的关系
        UpdateWrapper<AdminRoleRelation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("admin_id", adminId);
        adminRoleRelationMapper.delete(updateWrapper);
        // 建立新的关系
        if(CollUtil.isNotEmpty(roleIds)) {
            List<AdminRoleRelation> adminRoleList = new ArrayList<>();
            roleIds.forEach(roleId -> {
                AdminRoleRelation adminRole = new AdminRoleRelation();
                adminRole.setAdminId(adminId);
                adminRole.setRoleId(roleId);
                adminRoleList.add(adminRole);
            });
            adminRoleRelationService.saveBatch(adminRoleList);
        }
        return count;
    }
}
