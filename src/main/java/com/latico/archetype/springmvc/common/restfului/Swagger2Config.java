package com.latico.archetype.springmvc.common.restfului;

import com.latico.archetype.springmvc.Application;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <PRE>
 * Swagger2 配置
 * 1、restful的扫描基本包目录
 * 2、网页介绍信息
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-02-25 17:14:23
 * @Version: 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 是否开启swagger
     */
    private boolean swaggerShow = true;

    /**
     * 服务端口
     */
    private String serverPort = "8080";

    /**
     * 服务上下文
     */
    private String serverContextPath = "/";

    @Bean
    public Docket createRestApi() {
        String basePackage = Application.class.getPackage().getName();

        //添加扫描基本包目录
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @return 网页介绍信息
     */
    private ApiInfo apiInfo() {
        String serverContextPath = this.serverContextPath;
        if (!serverContextPath.startsWith("/")) {
            serverContextPath = "/" + serverContextPath;
        }
        if (!serverContextPath.endsWith("/")) {
            serverContextPath = serverContextPath + "/";
        }
        String ip = "localhost";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            ip = localHost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String url = "http://" + ip + ":" + serverPort + serverContextPath + "swagger-ui.html";
        String link = "<a href=\"" + url + "\" target=\"_blank\" title=\"Swagger Restful API\">" + url + "</a>";
        return new ApiInfoBuilder()
                .title("Swagger Restful API")
                .description("REST接口调测请访问:" + link)
                .termsOfServiceUrl("http://localhost:" + serverPort + serverContextPath + "swagger-ui.html")
                .contact("latico")
                .version("1.0")
                .build();
    }
}
