package com.example.web_demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

///**
// * 功能描述: WebMvc配置
// *
// * @author zhangam
// * @time 2019/5/26 13:39
// * @see
// **/
@Configuration
public class MyConfig implements WebMvcConfigurer {

    //    @Autowired
//    private MyInterceptor myInterceptor;
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedMethods("*");
//            }
//        };
//    }
//
//    @Bean
//    public ServletListenerRegistrationBean addListener() {
//        ServletListenerRegistrationBean listenerBean = new ServletListenerRegistrationBean();
//        listenerBean.setListener(new MyHttpSessionListener());
//        return listenerBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean addFilter() {
//        FilterRegistrationBean filterBean = new FilterRegistrationBean();
//        filterBean.setFilter(new MyFilter());
//        filterBean.addUrlPatterns("/test/*");
//        return filterBean;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInterceptor).addPathPatterns("/test/*").excludePathPatterns("/test/test1");
//    }
//
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/zam/**").addResourceLocations(ResourceUtils.FILE_URL_PREFIX + System.getProperty("user.dir") + "/zam/");
//        registry.addResourceHandler("/app/**").addResourceLocations(ResourceUtils.FILE_URL_PREFIX + System.getProperty("user.dir") + "/src/main/resources/");
//        registry.addResourceHandler("/app/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX);

        registry.addResourceHandler("/zam/**").addResourceLocations(ResourceUtils.FILE_URL_PREFIX + System.getProperty("user.dir") + "/src/main/resources/static/");
    }

}
