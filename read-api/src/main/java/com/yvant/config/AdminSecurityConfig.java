package com.yvant.config;

import com.yvant.common.security.config.SecurityConfig;
import com.yvant.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * 后台安全配置
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class AdminSecurityConfig extends SecurityConfig {

    @Autowired
    private IAdminService adminService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> adminService.loadUserByUsername(username);
    }


}
