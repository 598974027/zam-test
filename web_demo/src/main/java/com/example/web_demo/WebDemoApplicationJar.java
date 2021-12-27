package com.example.web_demo;

import io.micrometer.core.instrument.MeterRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.util.Properties;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableScheduling//定时任务
@EnableAsync//异步调用
@EnableCaching//缓存
@MapperScan("com.example.web_demo.dao")
@ComponentScan("com.example")
//如果不使用@MapperScan注解，还可以在每个 mapper 接口类上加上 @Mapper 这个注解，但是这样做比较麻烦，
//如果所有的mapper接口类都在一个包下，还是使用@MapperScan注解更为方便
public class WebDemoApplicationJar {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }

    public static void main(String[] args) {
//        SpringApplication.run(WebDemoApplicationJar.class, args);

//        SpringApplication springApplication = new SpringApplication(WebDemoApplicationJar.class);
//        springApplication.setDefaultProperties(new Properties());
//        springApplication.run(args);

        final ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(WebDemoApplicationJar.class).properties(new Properties()).run(args);
        try {
            System.out.println("本地http服务已启动，http://" + InetAddress.getLocalHost().getHostAddress() + ":" + applicationContext.getEnvironment().getPropertySources().get("server.ports").getProperty("local.server.port") + "/swagger-ui.html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true) {
//                    StringBuilder sb = new StringBuilder();
//                    sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ":");
//                    if (CountingUtil.getInstance().getOneCounting("send") != null) {
//                        sb.append(CountingUtil.getInstance().getOneCounting("send").getInfo() + ";");
//                    }
//                    System.out.println(sb.toString());
//                    try {
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }).start();

    }

}
