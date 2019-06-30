package com.latico.archetype.springmvc.controller;

import com.latico.archetype.springmvc.dao.entity.Demo;
import com.latico.archetype.springmvc.dao.mapper.DemoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <PRE>
 * 测试
 * 数据库查询接口等
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-03-10 16:13
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "test")
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Api(description = "测试数据库API")
public class TestDBController {

    /**
     * 测试Mybatis方式
     */
    @Autowired
    DemoMapper demoMapper;

    /**
     * @return 查询数据库所有Demo数据
     */
    @RequestMapping(value = "selectDemo")
    @ApiOperation("测试查询Demo表API")
    public List<Demo> selectDemo() {
        return demoMapper.findAll();
    }


}
