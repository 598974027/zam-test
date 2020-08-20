package com.notioni.webflux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: Test
 *
 * @author zhangaomin
 * @time 2020/8/20 9:50
 **/
public class ReactorTest {

    public static void main(String[] args) {
        Mono.fromSupplier(() -> "Hello").log().subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Hello")).log().subscribe(System.out::println);
        Mono.create(sink -> sink.success("Hello")).log().subscribe(System.out::println);

//        Flux.just("hello", "webflux", "spring", "boot")
//                .subscribe(System.out::println);

//        Flux.range(1, 10)
//                .timeout(Flux.never(), v -> Flux.never())
//                .subscribe(System.out::println);

//        Flux.interval(Duration.ofSeconds(2))
//                .doOnNext(System.out::println)
//                .blockLast();

//        Flux.create(sink -> {
//            for (int i = 0; i < 10; i++) {
//                sink.next(i);
//            }
//            sink.complete();
//        }).subscribe(x -> {
//            System.out.println(Thread.currentThread().getName() + " " + x);
//        });

        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).log().subscribe(System.out::println);

//        final Random random = new Random();
//        Flux.generate(ArrayList::new, (list, sink) -> {
//            int value = random.nextInt(5) + 1;
//            list.add(value);
//            sink.next(value);
//            if (list.size() == 20) {
//                sink.complete();
//            }
//            return list;
//        })
//                .log()
//                .doOnSubscribe(e -> System.out.println("doOnSubscribe--" + e))
//                .doOnRequest(e -> System.out.println("doOnRequest--" + e))
//                .doOnEach(e -> System.out.println("doOnEach--" + e))
//                .doOnNext(e -> System.out.println("doOnNext--" + e))
//                .doOnError(e -> System.out.println("doOnError--" + e))
//                .doOnTerminate(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println("--doOnTerminate--");
//                    }
//                })
//                .doOnCancel(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println("--doOnCancel--");
//                    }
//                })
//                .doOnComplete(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println("--doOnComplete--");
//                    }
//                }).subscribe(System.out::println);

//        Flux.just(1, 2, 3, 4)
//                .log()
//                .map(i -> {
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    return i * 2;
//                })
//                .subscribe(e -> System.out.println(e));

//        Flux.just(1, 2, 3, 4)
//                .log()
//                .flatMap(i -> Flux.just(i * 2).delayElements(Duration.ofSeconds(1)))
//                .subscribe(e -> System.out.println(e.toString()));

//        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
//        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
//        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);

//        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);

//        Flux.range(1, 100).window(20).subscribe(System.out::println);

//        Flux.just("a", "b")
//                .zipWith(Flux.just("c", "d"))
//                .subscribe(System.out::println);
//        Flux.just("a", "b")
//                .zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
//                .subscribe(System.out::println);

//        Flux.range(1, 1000).take(10).subscribe(System.out::println);
//        Flux.range(1, 1000).takeLast(10).subscribe(System.out::println);
//        Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);
//        Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);
//
//        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
//        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);

    }

}
