package com.notioni.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;

/**
 * 功能描述: HelloController
 *
 * @author zhangaomin
 * @time 2020/8/20 9:14
 **/
@RestController
@RequestMapping("/api/")
@Slf4j
public class HelloController {

    @GetMapping("flux")
    public Flux<String> flux() {
        return Flux.just("hello", "webflux", "spring", "boot");
    }

    @GetMapping("flux2")
    public Flux<Integer> flux2() {
        return Flux.range(1, 5).map(val -> val + 2).delayElements(Duration.ofSeconds(2));
    }

    @GetMapping("flux3")
    public Flux<String> flux3() {
        return Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        });
    }

    @GetMapping("flux4")
    public Flux<Integer> flux4() {
        return Flux.generate(ArrayList::new, (list, sink) -> {
            list.add("hello");
            sink.next(new Random().nextInt());
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        });
    }

    @GetMapping("flux5")
    public Flux<Integer> flux5() {
        return Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        });
    }

    @GetMapping("flux6")
    public Flux<String> flux6() {
//        return Flux.from(Mono.just("just"));
//        return Flux.fromArray(new String[] { "arr", "arr", "arr", "arr" })
        Set<String> v = new HashSet<>();
        v.add("1");
        v.add("2");
        v.add("3");
        return Flux.fromIterable(() -> v.iterator());
    }

    @GetMapping("mono")
    public Mono<String> mono() {
        return Mono.just("hello webflux");
    }

    @GetMapping("mono2")
    public Mono<Object> mono2() {
        return Mono.justOrEmpty(null);
    }

    @GetMapping("mono3")
    public Mono<Object> mono3() {
        return Mono.empty();
    }

}