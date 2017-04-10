package com.cmc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * javax.servlet.Filter Demo
 * 
 * @author HT.LCB
 * @version 2016年11月10日 上午10:17:59
 */
public class PrivateFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrivateFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 请求转发，因为是转发s给其他servlet进行处理，所以是通过ServletRequest（的方法ServletRequest.getRequestDispatcher(String location).forward(ServletRequest request, ServletResponse response)）实现
        // request.getRequestDispatcher("").forward(request, response);

        // 重定向，因为是要客户端重新请求（重定向），所以要使用HttpServletResponse（的方法HttpServletResponse.sendRedirect(String location)）实现
        // ((HttpServletResponse) response).sendRedirect("");  

        /**
         * 1. 把请求发送给下一个javax.servlet.Filter或者请求地址对应的资源
         * 2. 可以通过不调用javax.servlet.FilterChain.doFilter(ServletRequest request, ServletResponse response)终止当前线程的执行
         * 3. 也可以重定向或者转发当前请求
         */

        chain.doFilter(request, response);
        LOGGER.info("enter into PrivateFilter.");
    }

    @Override
    public void destroy() {
    }

}