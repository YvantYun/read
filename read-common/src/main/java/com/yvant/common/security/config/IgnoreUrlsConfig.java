package com.yvant.common.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用于配置不需要保护的资源路径
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-24
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "security.ignored")
public class IgnoreUrlsConfig {
    private List<String> urls = new ArrayList<>();

}
