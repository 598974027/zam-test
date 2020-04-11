package com.example.web_demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * 功能描述: WebMvc配置
 *
 * @author zhangam
 * @time 2019/5/26 13:39
 * @see
 **/
//@Configuration
public class MyConfig implements WebMvcConfigurer {

//    @Autowired
    private MyFilter myFilter;

//    @Autowired
    private MyInterceptor myInterceptor;

//    @Bean
//    public FilterRegistrationBean addFilter() {
//        FilterRegistrationBean frBean = new FilterRegistrationBean();
//        frBean.setFilter(myFilter);
//        frBean.addUrlPatterns("/test/*");
//        return frBean;
//    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //匹配结尾 / :会识别 url 的最后一个字符是否为 /
        //localhost:8080/test与localhost:8080/test/等价
//        configurer.setUseTrailingSlashMatch(true);
        //匹配后缀名：会识别 xx.* 后缀的内容
        //localhost:8080/test与localhost:8080/test.jsp等价
//        configurer.setUseSuffixPatternMatch(true);
    }

//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(true)
//                /* 不检查Accept请求头 */
//                .ignoreAcceptHeader(true)
//                .parameterName("mediaType")
//                /* 设置默认的media yype */
//                .defaultContentType(MediaType.TEXT_HTML)
//                /* 请求以.html结尾的会被当成MediaType.TEXT_HTML*/
//                .mediaType("html", MediaType.TEXT_HTML)
//                /* 请求以.json结尾的会被当成MediaType.APPLICATION_JSON*/
//                .mediaType("json", MediaType.APPLICATION_JSON);
//    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/zam/**").addResourceLocations("file:" + System.getProperty("user.dir") + "/zam/"); //媒体资源
//        registry.addResourceHandler("/app/**").addResourceLocations("file:" + System.getProperty("user.dir") + "/src/main/resources/");
//        registry.addResourceHandler("/app/**").addResourceLocations("classpath:/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }

}
