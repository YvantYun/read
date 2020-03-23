package com.yvant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yvant.mapper.admin.AdminMapper;
import com.yvant.model.admin.Admin;
import com.yvant.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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



}
