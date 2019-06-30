package com.latico.archetype.springmvc.config.profiles;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * <PRE>
 * 开发环境
 * </PRE>
 *
 * @author zhangmaolin
 * @version 1.0.0
 * @since 2018-12-14 16:35
 */
@Configuration
@PropertySource(value = {"classpath:config/application-dev.properties"})
@Profile("dev")
public class DevProfiles {
}
