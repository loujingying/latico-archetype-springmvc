package com.latico.archetype.springmvc.config.springconfig;

import com.latico.archetype.springmvc.config.SpringConfigContants;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * <PRE>
 *  初始化 分发器/前端控制器 DispatcherServlet
 * </PRE>
 * @Author: latico
 * @Date: 2019-03-27 17:43:55
 * @Version: 1.0
 */
public class DispatcherServletInitializerImpl extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定 Root WebApplicationContext 类，这个类必须@Configuration来注解，从而代替XML配置文件
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SpringRootConfig.class};
    }

    /**
     * 指定 Servlet WebApplicationContext 类，这个类必须@Configuration来注解，从而代替XML配置文件
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringMvcConfig.class};
    }

    /**
     * 指定 Servlet mappings
     * 问：　/和/*　有什么区别？
     * 　 答： /会拦截除了jsp以外的所有url，/* 会拦截所有url，包括jsp。
     * 例如：在webroot下面有一个test.jsp,当DispatcherServlet 配置映射/ 时，
     * 浏览器输入：http://localhost:8083/test.jsp 这个jsp是可以直接访问的，并且不经过DispatcherServlet ，
     * 而当DispatcherServlet 配置映射/* 时，这个请求就会被DispatcherServlet 拦截。
     */
    @Override
    protected String[] getServletMappings() {
        return SpringConfigContants.ServletMappings;
    }

    /**
     * 启动,加载过滤器
     *
     * @param servletContext ServletContext
     * @throws ServletException ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //如果不加上这句话,则会无法启动
        super.onStartup(servletContext);
        //　　forceEncoding ：是否允许设置的encoding 覆盖request和response中已经存在的encodings。
        servletContext.addFilter("CharacterEncodingFilter", new CharacterEncodingFilter(SpringConfigContants.Character, true))
                .addMappingForUrlPatterns(null, false, "/*");

        //默认为开启正式环境配置文件
        servletContext.setInitParameter("spring.profiles.default", SpringConfigContants.default_profiles);
    }
}