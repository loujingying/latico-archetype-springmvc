package com.latico.archetype.springmvc.controller;

import com.latico.archetype.springmvc.bean.bo.DemoTimeParam;
import com.latico.archetype.springmvc.bean.dto.RestRequestDTO;
import com.latico.archetype.springmvc.bean.dto.RestResponseDTO;
import com.latico.archetype.springmvc.service.DemoService;
import com.latico.commons.common.util.json.JacksonUtils;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * <PRE>
 *  演示
 *  启动后的访问方式有：
 *  1、浏览器；
 *  2、客户端API；
 *  3、Postman；
 *  4、SoapUI
 *
 * </PRE>
 * @Author: latico
 * @Date: 2019-06-07 01:33:49
 * @Version: 1.0
 */
@RestController
@RequestMapping("demo")
@Api(description = "演示API")
public class DemoController {

    private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    DemoService demoService;

    /**
     * @return 对象类型数据
     */
    @RequestMapping("serverTime")
    @ApiOperation("获取服务器时间参数API")
    public DemoTimeParam serverTime() {
        return demoService.serverTime();
    }

    /**
     * @return 字符串类型数据
     */
    @RequestMapping(value = "serverTimeStr")
    @ApiOperation("获取字符串类型服务器时间API")
    public String serverTimeStr() {
        //返回字符串，需要包一层JSON
        return JacksonUtils.objToJson("字符串类型:" + demoService.serverTime());
    }

    /**
     * @return 对象类型数据
     */
    @RequestMapping("serverTimeBean")
    @ApiOperation("获取服务器时间API")
    public RestResponseDTO<DemoTimeParam> serverTimeBean(@RequestBody RestRequestDTO<DemoTimeParam> restRequestDTO) {
        RestResponseDTO<DemoTimeParam> restResponseDTO = new RestResponseDTO<>();
        System.out.println("接收到的时间:" + restRequestDTO);
        DemoTimeParam demoTimeParam = demoService.serverTime();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        demoTimeParam.setTime(new Date(System.currentTimeMillis()));
        System.out.println("响应的时间:" + demoTimeParam);
        restResponseDTO.setParam(demoTimeParam);
        restResponseDTO.setId(restRequestDTO.getId());
        return restResponseDTO;
    }

    /**
     * 测试URL中的键值对参数，可以多个 RequestParam
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testRequestParam")
    @ApiOperation("测试请求参数API")
    public String testRequestParam(@RequestParam("name") String name) {
        //返回字符串，需要包一层JSON
        return JacksonUtils.objToJson("测试RequestParam:" + name);
    }

    /**
     * 测试路径中的参数 PathVariable
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testPathVariable/{name}")
    @ApiOperation("获测试路径变量API")
    public String testPathVariable(@PathVariable String name) {
        //返回字符串，需要包一层JSON
        return JacksonUtils.objToJson("测试PathVariable:" + name);
    }

    /**
     * 测试路径中的多参数 多路径参数，PathVariable
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testMultiPathVariable/{name}/{value}")
    @ApiOperation("测试路径多变量API")
    public String testMultiPathVariable(@PathVariable Map<String, String> map) {
        //返回字符串，需要包一层JSON
        return JacksonUtils.objToJson("测试testMultiPathVariable:" + map.get("name") + "/" + map.get("value"));
    }
}
