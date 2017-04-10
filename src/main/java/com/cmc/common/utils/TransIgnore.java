/**
 * 
 */
package com.cmc.common.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName TransIgnore 
 * @Description 标识在进行 dto-domain 转换时忽略的字段.
 * 使用方法：在需要忽略的字段上加上@TransIgnore注解，则此字段不会在进行 dto-domain 转换时进行转换.
 * 此注解用在 dataobject 端.
 * @author plz
 * @date 2015年6月19日 上午10:36:50
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TransIgnore {

}
