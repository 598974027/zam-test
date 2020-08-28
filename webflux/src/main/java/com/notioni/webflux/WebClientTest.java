package com.notioni.webflux;

import com.notioni.webflux.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Iterator;

/**
 * 功能描述: WebClientTest
 *
 * @author zhangaomin
 * @time 2020/8/21 9:07
 **/
public class WebClientTest {

    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://localhost:8080");

        Mono<Student> mono = webClient.get().uri("/students/findOne/S0001")
                .retrieve().bodyToMono(Student.class);
        mono.subscribe(s -> {
            System.out.println("mono subscribe " + s.getName());
        });
        Student student = mono.block();
        System.out.println("mono block " + student.getName());

        Flux<Student> flux = webClient.get().uri("/students/findAll")
                .retrieve().bodyToFlux(Student.class);
        flux.subscribe(s -> {
            System.out.println("flux subscribe " + s.getName());
        });
        Iterator<Student> iterator = flux.toIterable().iterator();
        while (iterator.hasNext()) {
            System.out.println("flux iterator " + iterator.next().getName());
        }
    }

}
