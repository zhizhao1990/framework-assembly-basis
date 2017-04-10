package com.cmc.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model和DTO转换工具
 * @author lcb
 * @version 2016/06/03 15:47
 */
public class ModelDataObjectUtil {

    private static Logger logger = LoggerFactory.getLogger(ModelDataObjectUtil.class);

    public static <T> T model2do(Object model, Class<T> dataObjectClass) {
        if (null == model || null == dataObjectClass) {
            return null;
        }
        T dataObject = null;
        try {
            dataObject = dataObjectClass.newInstance();
        } catch (InstantiationException e1) {
            logger.error(e1.getMessage(), e1);
        } catch (IllegalAccessException e2) {
            logger.error(e2.getMessage(), e2);
        }
        if (null == dataObject) {
            return null;
        }
        List<Field> fields = getAllField(new ArrayList<Field>(), dataObject.getClass(), 1);
        for (Field field : fields) {
            try {
                // 此字段标记为转换时忽略
                if (field.isAnnotationPresent(TransIgnore.class)) {
                    continue;
                }
                if (field.isAnnotationPresent(Desensitize.class)) {
                    Desensitize desensitize = field.getAnnotation(Desensitize.class);
                    String dtoFieldName = desensitize.fieldName();
                    if (StringUtils.isBlank(dtoFieldName)) {
                        dtoFieldName = field.getName();
                    }
                    Field modelField = getFiledByName(model.getClass(), dtoFieldName);
                    field.setAccessible(true);
                    modelField.setAccessible(true);
                    Object fieldValue = null;
                    Object dtoFieldValue = modelField.get(model);
                    if (null == dtoFieldValue) {
                        fieldValue = null;
                    } else {
                        String fieldStrValue = IdHandler.idDecrypt(dtoFieldValue.toString());
                        // field 类型暂时只能处理 Long，Integer，String
                        if (Long.class.equals(field.getType())) {
                            fieldValue = new Long(fieldStrValue);
                        } else if (Integer.class.equals(field.getType())) {
                            fieldValue = new Integer(fieldStrValue);
                        } else {
                            fieldValue = fieldStrValue;
                        }
                        logger.debug(dtoFieldName + ":" + fieldValue);
                    }
                    field.set(dataObject, fieldValue);
                } else {
                    // model field handle
                    Field modelField = getFiledByName(model.getClass(), field.getName());
                    field.setAccessible(true);
                    modelField.setAccessible(true);
                    field.set(dataObject, modelField.get(model));
                }
            } catch (NumberFormatException e) {
                logger.error("数据转换错误", e);
            } catch (Exception e) {
                logger.warn(e.getMessage());
                continue;
            }
        }
        return dataObject;
    }

    public static <T> T do2model(Object dataObject, Class<T> modelClass) {

        if (dataObject == null || null == modelClass) {
            return null;
        }
        T model = null;
        try {
            model = modelClass.newInstance();
        } catch (InstantiationException e1) {
            logger.error(e1.getMessage(), e1);
        } catch (IllegalAccessException e1) {
            logger.error(e1.getMessage(), e1);
        }

        List<Field> fields = new ArrayList<Field>();
        getAllField(fields, dataObject.getClass(), 1);
        for (Field field : fields) {
            try {
                // 此字段标记为转换时忽略
                if (field.isAnnotationPresent(TransIgnore.class)) {
                    continue;
                }
                if (field.isAnnotationPresent(Desensitize.class)) {
                    Desensitize desensitize = field.getAnnotation(Desensitize.class);
                    String dtoFieldName = desensitize.fieldName();
                    if (StringUtils.isBlank(dtoFieldName)) {
                        dtoFieldName = field.getName();
                    }
                    field.setAccessible(true);
                    Object fieldValue = field.get(dataObject);
                    Object dtoFieldValue;
                    if (null == fieldValue) {
                        dtoFieldValue = null;
                    } else {
                        dtoFieldValue = IdHandler.idEncrypt(fieldValue.toString());
                        logger.debug(dtoFieldName + ":" + dtoFieldValue.toString());
                    }
                    Field dtoField = getFiledByName(model.getClass(), dtoFieldName);
                    dtoField.setAccessible(true);
                    dtoField.set(model, dtoFieldValue);
                } else {
                    Field modelField = getFiledByName(model.getClass(), field.getName());

                    field.setAccessible(true);
                    modelField.setAccessible(true);
                    modelField.set(model, field.get(dataObject));
                }
            } catch (Exception e) {
                logger.warn(e.getMessage());
                continue;
            }
        }

        return model;

    }

    /**
    * @Description model列表转换dataobject列表
    * @param models
    *            model列表
    * @param dataObjectClass
    *            dataobject.class
    * @return dataobject列表
    */
    public static <T, M> List<T> modelList2doList(List<M> models, Class<T> dataObjectClass) {
        if (null == models) {
            return null;
        }
        List<T> dataObjects = new ArrayList<T>();
        for (M model : models) {
            dataObjects.add(model2do(model, dataObjectClass));
        }
        return dataObjects;
    }

    /**
     * @Description dataobject列表转换model列表
     * @param dataObjects
     *            dataobject列表
     * @param modelClass
     *            model.class
     * @return model列表
     */
    public static <T, M> List<T> doList2modelList(List<M> dataObjects, Class<T> modelClass) {
        if (null == dataObjects) {
            return null;
        }
        List<T> models = new ArrayList<T>();
        for (M dataobject : dataObjects) {
            models.add(do2model(dataobject, modelClass));
        }
        return models;
    }

    private static List<Field> getAllField(List<Field> list, Class<?> currentClass, int i) {
        if (null == list) {
            list = new ArrayList<Field>();
        }
        if (i >= 5 || null == currentClass) { // 5 是用来做什么的？？？
            return list;
        }
        try {
            Class<?> superClass = currentClass.getSuperclass();
            Field[] field = currentClass.getDeclaredFields();
            if (null != field) {
                CollectionUtils.addAll(list, field);
            }
            return getAllField(list, superClass, ++i);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return list;
        }
    }

    private static Field getFiledByName(Class<?> clazz, String fieldName) {
        if (null == clazz || StringUtils.isBlank(fieldName)) {
            return null;
        }
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (SecurityException e) {
            logger.error(e.getMessage(), e);
        } catch (NoSuchFieldException e) {
            logger.warn("Can not find field namded " + fieldName + " in class" + clazz);
        }
        if (field == null && clazz.getSuperclass() != null) {
            return getFiledByName(clazz.getSuperclass(), fieldName);
        } else {
            return field;
        }
    }

}
