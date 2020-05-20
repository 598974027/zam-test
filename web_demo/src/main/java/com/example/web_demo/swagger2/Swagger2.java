package com.example.web_demo.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/2/21 16:52
 * @see [相关类/方法]
 * @since [产品/模块版本]
 **/
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public static SwaggerInfo setSwaggerInfo() {
        SwaggerInfo swaggerInfo = new SwaggerInfo();
        swaggerInfo.setTitle("接口服务");
        swaggerInfo.setContact("zam");
        swaggerInfo.setVersion("1.0");
        return swaggerInfo;
    }

    /**
     * 功能描述: 创建RestApi
     *
     * @author zhangam
     * @date 2019/2/21 19:08
     * @since
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 功能描述: api显示信息
     *
     * @author zhangam
     * @date 2019/2/21 19:08
     * @since
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(setSwaggerInfo().getTitle())
                .description(setSwaggerInfo().getDescription())
                .termsOfServiceUrl(setSwaggerInfo().getTermsOfServiceUrl())
                .contact(setSwaggerInfo().getContact())
                .version(setSwaggerInfo().getVersion())
                .build();
    }

}
