package com.example.web_demo.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/30 21:35
 * @see
 **/
public class ReactorTests {

    public static void main(String[] args) {
        new ReactorTests().testFlux();
//        new ReactorTests().testMono();
    }

    public void testFlux() {
//        Flux.just("Hello", "World").subscribe(System.out::println);
//        Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
//        Flux.empty().subscribe(System.out::println);
//        Flux.range(1, 10).subscribe(System.out::println);
//        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
//
//        Flux.generate(sink -> {
//            sink.next("Hello");
//            sink.complete();
//        }).subscribe(System.out::println);
//
//        Flux.create(sink -> {
//            for (int i = 0; i < 10; i++) {
//                sink.next(i);
//            }
//            sink.complete();
//        }).subscribe(System.out::println);

        Flux.create(sink -> {
            for (int i = 0; i < 3000; i++) {
                Student s = new Student();
                s.id = i;
                s.name = i + "zam";
                sink.next(s);
            }
            sink.complete();
        }).parallel(5)
                .runOn(Schedulers.parallel())
                .map(x -> x.toString() + "----" + Thread.currentThread().getName())
                .subscribe(System.out::println);
    }

    public void testMono() {
        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
    }

}
