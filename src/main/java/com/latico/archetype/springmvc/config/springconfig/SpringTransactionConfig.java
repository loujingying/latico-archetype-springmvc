package com.latico.archetype.springmvc.config.springconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <PRE>
 * 事务配置
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-03-30 15:37
 * @Version: 1.0
 */
@EnableTransactionManagement
public class SpringTransactionConfig {
    @Autowired
    private DruidDataSource druidDataSource;

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(druidDataSource);
    }

//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

}
