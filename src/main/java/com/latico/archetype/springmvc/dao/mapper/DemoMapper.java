package com.latico.archetype.springmvc.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.latico.archetype.springmvc.dao.entity.Demo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author landingdong
 * @since 2019-02-24
 */
public interface DemoMapper extends BaseMapper<Demo> {
    List<Demo> findAll();
}
