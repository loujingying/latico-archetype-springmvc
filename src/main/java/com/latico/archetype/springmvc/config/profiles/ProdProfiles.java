package com.latico.archetype.springmvc.config.profiles;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * <PRE>
 * 生产环境
 * </PRE>
 *
 * @author zhangmaolin
 * @version 1.0.0
 * @since 2018-12-14 16:39
 */
@Configuration
@PropertySource(value = {"classpath:config/application.properties"})
@Profile("prod")
public class ProdProfiles {
}
