package com.latico.archetype.springmvc.config.springconfig;

import com.latico.archetype.springmvc.config.SpringConfigContants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.List;

/**
 * <PRE>
 通过@EnableWebMvc来导入Spring MVC的相关配置，通过@ComponentScan来自动扫描控制器类HomeController所在的包。
 同样，@Configuration用来说明这个是配置类用来替换原来的xml
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-03-27 22:22:20
 * @Version: 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {SpringConfigContants.SpringMvcConfig_Controller_Package, SpringConfigContants.SpringMvcConfig_Service_Package, SpringConfigContants.SpringMvcConfig_apiUi_basePackages})
//为了让事务生效在本类的扫描范围，需要导入事务配置类，一个java配置类加上另外一个配置类使用@Import(AppConfig1.class)，AppConfig1可以不加@Configuration注解
@Import(value = SpringTransactionConfig.class)
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置视图解析器，跟xml配置一样，配置前缀和后缀
     *
     * @return ViewResolver
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(SpringConfigContants.SpringMvcConfig_ViewResolver_Prefix);
        viewResolver.setSuffix(SpringConfigContants.SpringMvcConfig_ViewResolver_Suffix);
        return viewResolver;
    }

    /**
     * 配置静态文件处理器
     * 当DisptacherServlet接收到了他匹配的请求，但是找不到相应的Controller，
     * 就会把这个请求返回给默认的处理（比如交给tomcat处理）

     此时会注册一个默认的Handler：DefaultServletHttpRequestHandler，这个Handler也是用来处理静态文件的，
     它会尝试映射/*。

     当DispatcherServelt映射/时（/ 和/* 是有区别的），并且没有找到合适的Handler来处理请求时，就会交给DefaultServletHttpRequestHandler 来处理。注意：这里的静态资源是放置在web根目录下，     而非WEB-INF 下。
     　　可能这里的描述有点不好懂（我自己也这么觉得），所以简单举个例子，

     例如：在webroot目录下有一个图片：1.png 我们知道Servelt规范中web根目录（webroot）下的文件可以直接访问的，
     但是由于DispatcherServlet配置了映射路径是：/* ，它几乎把所有的请求都拦截了，从而导致1.png 访问不到，
     这时注册一个DefaultServletHttpRequestHandler 就可以解决这个问题。
     其实可以理解为DispatcherServlet破坏了Servlet的一个特性（根目录下的文件可以直接访问），
     DefaultServletHttpRequestHandler是帮助回归这个特性的。

     问：　/和/*　有什么区别？
     * 　 答： /会拦截除了jsp以外的所有url，/* 会拦截所有url，包括jsp。
     * 例如：在webroot下面有一个test.jsp,当DispatcherServlet 配置映射/ 时，
     * 浏览器输入：http://localhost:8080/test.jsp 这个jsp是可以直接访问的，并且不经过DispatcherServlet ，
     * 而当DispatcherServlet 配置映射/* 时，这个请求就会被DispatcherServlet 拦截。
     *
     * 因为{@link DispatcherServletInitializerImpl#getServletMappings()} 配置了是/ 所以不需要下面的代码
     * @param configurer DefaultServletHandlerConfigurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 解决客户端中文乱码问题
     * 解决controller返回字符串时的中文乱码
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setDefaultCharset(Charset.forName(SpringConfigContants.Character));
        converters.add(converter);
    }

}