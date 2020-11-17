package com.example.web_demo.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Bean
    public Docket createRestApi() {
        //添加head参数
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder range = new ParameterBuilder();
        range.name("Range").description("下载范围").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameters.add(range.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(null)
                .securityContexts(Arrays.asList(securityContexts()))
//                .globalOperationParameters(parameters)
                .apiInfo(new ApiInfoBuilder()
                        .title(setSwaggerInfo().getTitle())
                        .description(setSwaggerInfo().getDescription())
                        .termsOfServiceUrl(setSwaggerInfo().getTermsOfServiceUrl())
                        .contact(setSwaggerInfo().getContact())
                        .version(setSwaggerInfo().getVersion())
                        .build());
    }

    private SecurityScheme securitySchemes() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContexts() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = new AuthorizationScope("xxx", "描述信息");
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

}
