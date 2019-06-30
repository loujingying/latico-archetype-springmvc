package com.latico.archetype.springmvc.config;

/**
 * <PRE>
 * 配置常量，包路径配置，启动扫描需要用到
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-03-30 16:01
 * @Version: 1.0
 */
public class SpringConfigContants {
    /**
     * 根容器扫描的目录
     */
    public static final String SpringRootConfig_basePackages = "com.latico.archetype.springmvc.config";

    /**
     * servlet的映射
     */
    public static final String[] ServletMappings = {"/"};
    /**
     * 字符集
     */
    public static final String Character = "UTF-8";
    /**
     * 默认的配置
     */
    public static final String default_profiles = "prod";
    /**
     * Mapper接口类
     */
    public static final String MyBatisConfig_MapperClassPackage = "com.latico.archetype.springmvc.dao.mapper";
    /**
     * Mapper文件
     */
    public static final String MapperLocationsPattern = "classpath*:mapper/**/*Mapper.xml";
    /**
     * 实体类
     */
    public static final String TypeAliasesPackage = "com.latico.archetype.springmvc.dao.entity";
    /**
     * 控制器包路径
     */
    public static final String SpringMvcConfig_Controller_Package = "com.latico.archetype.springmvc.controller";
    /**
     * 业务基本包
     */
    public static final String SpringMvcConfig_Service_Package = "com.latico.archetype.springmvc.service";
    /**
     * 因为swagger需要扫描rest接口，所以需要放在springmvc的位置，放在SpringRootConfig不生效
     */
    public static final String SpringMvcConfig_apiUi_basePackages = "com.latico.archetype.springmvc.common.restfului";
    /**
     * mvc的ViewResolver的前缀
     */
    public static final String SpringMvcConfig_ViewResolver_Prefix = "/WEB-INF/view/";
    /**
     * mvc的ViewResolver的后缀
     */
    public static final String SpringMvcConfig_ViewResolver_Suffix = ".jsp";
}
