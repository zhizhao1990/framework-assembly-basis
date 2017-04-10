package com.cmc.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper接口
 * @param <T> Entity 泛型
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}