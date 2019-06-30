package com.latico.archetype.springmvc.config.springconfig;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-03-30 14:34
 * @Version: 1.0
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.latico.archetype.springmvc.config.SpringConfigContants;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * <PRE>
 *  mybatis配置
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-03-30 16:00:02
 * @Version: 1.0
 */
@Configuration
@MapperScan(
        value = {SpringConfigContants.MyBatisConfig_MapperClassPackage},  // 指定mapper接口存放的包
        sqlSessionFactoryRef = "sqlSessionFactory"
)
public class MyBatisConfig {
    @Value("${helperDialect}")
    private String helperDialect;

    @Autowired
    private DruidDataSource druidDataSource;

    /**
     * pageHelper的分页插件
     *
     * @return
     */
    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor interceptor = new PageInterceptor();
        Properties props = new Properties();
        props.setProperty("helperDialect", helperDialect);
        props.setProperty("reasonable", "true");
        props.setProperty("rowBoundsWithCount", "true");
        props.setProperty("offsetAsPageNum", "true");
        props.setProperty("pageSizeZero", "true");
        props.setProperty("autoRuntimeDialect", "true");
        interceptor.setProperties(props);
        return interceptor;
    }

    /**
     * 配置Session工厂
     *
     * @return SqlSessionFactoryBean
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 2. 设置mapper配置文件的位置， new ClassPathResource("/mapper/*.xml")表示加载类路径下的mapper文件夹下的 .xml文件
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(SpringConfigContants.MapperLocationsPattern));
        // 3. 设置实体类的别名
        sqlSessionFactoryBean.setTypeAliasesPackage(SpringConfigContants.TypeAliasesPackage);
        // 4. 添加mybatis pageHelper分页插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor()});

        return sqlSessionFactoryBean;
    }
}
