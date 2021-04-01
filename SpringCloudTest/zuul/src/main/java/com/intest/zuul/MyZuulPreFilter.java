package com.intest.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述: 自定义过滤器
 *
 * @author zhangam
 * @time 2020/5/15 15:25
 * @see
 **/
public class MyZuulPreFilter extends ZuulFilter {

    /**
     * 4种不同生命周期的过滤器类型
     * pre route error post
     * pre过滤器： 在请求被路由之前调用。Zuul请求微服务之前。比如请求身份验证，选择微服务实例，记录调试信息等
     * route过滤器： 负责转发请求到微服务。原始请求在此构建，并使用Apache HttpClient或Netflix Ribbon发送原始请求
     * error过滤器： 在处理请求发生错误时被调用
     * post过滤器： 在route和error过滤器之后被调用。可以在响应添加标准HTTP Header、收集统计信息和指标，以及将响应发送给客户端等
     *
     * @return
     */
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
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getParameter("token");
        if (token == null || token.isEmpty() || !token.equals("123456")) {
            //不对该请求进行路由，不会将请求转发到后端
            currentContext.setSendZuulResponse(false);
            //设置错误状态码
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            //设置响应体
            currentContext.setResponseBody("权限不足.....");
            currentContext.getResponse().setContentType("text/html;charset=UTF-8");
        }
        return null;
    }

}