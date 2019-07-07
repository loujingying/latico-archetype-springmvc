package com.latico.archetype.springmvc.config.springconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <PRE>
 * 数据源配置
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-29 17:51
 * @Version: 1.0
 */
@Configuration
public class SpringJdbcConfig {

    @Value("${jdbc.driver}")
    private String driverClass;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;


    /**
     * 配置数据源
     * initMethod: 初始化数据源
     * destroyMethod： 关闭数据源
     *
     * @return DataSource
     */
    @Bean(initMethod = "init" ,destroyMethod = "close")
    public DruidDataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setMinIdle(1);
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(20);
        return dataSource;
    }


}
