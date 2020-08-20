package com.intest.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class Controller {

    @Value("${server.port}")
    private int port;

    @Value("${zam.abc}")
    private int abc;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String home() {
        discoveryClient.getServices().forEach(o -> {
            discoveryClient.getInstances(o).forEach(p -> {
                System.out.println(p.getUri());
            });
        });
        System.out.println(abc);
        return "hello, service provider port is from:" + port;
    }

}
