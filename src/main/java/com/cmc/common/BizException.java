package com.cmc.common;

/**
 * （公共）业务异常类
 * 
 * <p>此异常类仅仅是一个公共异常类DEMO
 * 
 * <p> 参考文档
 * <li>http://blog.csdn.net/stellaah/article/details/6738424
 * 
 * @author Thomas Lee
 * @since 2016年12月22日 上午9:40:27
 */
public class BizException extends Exception {

    private static final long serialVersionUID = 6826419480163223312L;

    public BizException(String msg) {
        super(msg);
    }

}