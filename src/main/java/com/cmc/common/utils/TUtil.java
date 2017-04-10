package com.cmc.common.utils;

import java.lang.reflect.ParameterizedType;

import com.cmc.user.service.biz.impl.UserServiceImpl;

public class TUtil {

    /**
     * 泛型转换
     * <p>
     * - 暂时放到反射之后进行研究 - 学习完成之后进一步封装基本的CRUD业务操作（面向接口编程）
     * <p>
     * @param entity
     * @return
     * @author HT.LCB
     * @version 2016年10月27日 下午1:55:32
     */
    public static Class<?> getActualType(Class<?> entity) {
        ParameterizedType parameterizedType = (ParameterizedType) entity.getGenericSuperclass();
        return (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }

    public static void main(String[] args) {
        System.out.println(getActualType(new UserServiceImpl().getClass()));
    }

}