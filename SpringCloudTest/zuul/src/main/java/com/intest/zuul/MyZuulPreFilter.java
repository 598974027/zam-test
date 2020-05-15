package com.intest.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述: 自定义过滤器
 *
 * @author zhangam
 * @time 2020/5/15 15:25
 * @see
 **/
public class MyZuulPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        System.out.println("请求的URI:" + request.getRequestURI());
        return null;
    }

}