package com.latico.archetype.springmvc.config.springconfig;

import com.latico.archetype.springmvc.config.SpringConfigContants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * <PRE>
 *     顶层容器配置
 * 应用中有多个DispatcherServlet时，每个DispatcherServlet的bean如Controller，
 * ViewResolver,HandlerMapping等，均在getServletConfigClasses返回的类中配置而一些公共的bean，
 * 如Services，Repository均在getRootConfigClasses返回的类中配置
 * RootConfig类使用了@Configuration作为注解，而@Configuration本身是使用了@Component进行了注解。
 * 所以这个RootConfig类是可以被组件扫描到并注入到容器中的。@Configuration用来说明这个是配置类用来替换原来的xml。
 * 为了实现组件自动扫描和实例化并注入到容器中，我们要在配置类中加上@ComponnetScan注解，并设定扫描的包的范围。
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-03-27 22:19:25
 * @Version: 1.0
 */
@Configuration
@ComponentScan(basePackages = {SpringConfigContants.SpringRootConfig_basePackages},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class SpringRootConfig {
}