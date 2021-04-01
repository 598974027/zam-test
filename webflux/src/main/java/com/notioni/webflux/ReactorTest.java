package com.notioni.webflux;


import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import org.springframework.http.converter.json.GsonBuilderUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: Test
 *
 * @author zhangaomin
 * @time 2020/8/20 9:50
 **/
public class ReactorTest {

    public static void main(String[] args) throws InterruptedException {
//        Hooks.onOperatorDebug();

//        Mono.fromSupplier(() -> 23)
//                .log()
//                .subscribe(System.out::println);
//        System.out.println("********************************************************************");
//        Mono.just(Optional.of("Hello"))
//                .subscribe(System.out::println);
//        Mono.empty()
//                .subscribe(System.out::println);
//        Mono.justOrEmpty(Optional.of("Hello"))
//                .subscribe(System.out::println);
//        System.out.println("********************************************************************");
//        Mono.create(sink -> sink.success(23.23f))
//                .subscribe(System.out::println);
//        System.out.println("********************************************************************");
//
//        Flux.just("hello", "webflux", "spring", "boot")
//                .subscribe(System.out::println);
//        System.out.println("********************************************************************");
//        Flux.empty()
//                .subscribe(System.out::println);
//        System.out.println("********************************************************************");
//        Flux.from(Mono.just("hello"))
//                .subscribe(System.out::println);
//        System.out.println("********************************************************************");
//        Flux.fromArray(new String[]{"hello", "webflux", "spring", "boot"})
//                .subscribe(System.out::println);
//        System.out.println("********************************************************************");
//        Set<String> v = new HashSet<>();
//        v.add("1");
//        v.add("2");
//        Flux.fromIterable(() -> v.iterator()).subscribe(System.out::println);
//        System.out.println("********************************************************************");
//        Flux.range(1, 5)
//                .subscribe(System.out::println);
//        System.out.println("********************************************************************");
//        Flux.interval(Duration.of(10, ChronoUnit.SECONDS))
//                .subscribe(System.out::println);
//        System.out.println("********************************************************************");
//        Flux.create(sink -> {
//            for (int i = 0; i < 10; i++) {
//                sink.next(i);
//            }
//            sink.complete();
//        }).subscribe(x -> {
//            System.out.println(Thread.currentThread().getName() + " " + x);
//        });
//        System.out.println("********************************************************************");
//        Flux.generate(sink -> {
//            sink.next("Hello");
//            sink.complete();
//        }).subscribe(System.out::println);
//        System.out.println("********************************************************************");
//        final Random random = new Random();
//        Flux.generate(ArrayList::new, (list, sink) -> {
//            int value = random.nextInt(100);
//            list.add(value);
//            sink.next(value);
//            if (list.size() == 10) {
//                sink.complete();
//            }
//            return list;
//        })
//                .doOnSubscribe(e -> System.out.println("doOnSubscribe--" + e))
//                .doOnRequest(e -> System.out.println("doOnRequest--" + e))
//                .doOnEach(e -> System.out.println("doOnEach--" + e))
//                .doOnNext(e -> System.out.println("doOnNext--" + e))
//                .doOnError(e -> System.out.println("doOnError--" + e))
//                .doOnTerminate(new Runnable() {
//                    @Override
//                    public void run() {
//                    }
//                })
//                .doOnCancel(new Runnable() {
//                    @Override
//                    public void run() {
//                    }
//                })
//                .doOnComplete(new Runnable() {
//                    @Override
//                    public void run() {
//                    }
//                }).subscribe(System.out::println);
//        System.out.println("********************************************************************");

//        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
//        Flux.range(1, 100).bufferTimeout(50, Duration.ofMillis(1)).subscribe(System.out::println);

//        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);

//        Flux.range(1, 100).window(20).subscribe(s -> {
//            s.filter(i -> i % 2 == 0).subscribe(System.out::println);
//        });

//        Flux.just("a", "b", "c").zipWith(Flux.just("c", "d")).subscribe(System.out::println);
//        Flux.just("a", "b").zipWith(Flux.just("c", "d", "e"), (s1, s2) -> String.format("%s-%s", s1, s2)).subscribe(System.out::println);

//        Flux.range(1, 1000).take(10).subscribe(System.out::println);
//        Flux.range(1, 1000).takeLast(10).subscribe(System.out::println);
//        Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);
//        Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);

//        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
//        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);

//        Flux.merge(Flux.range(0, 100).take(5), Flux.range(50, 100).take(5)).subscribe(System.out::println);

//        Flux.just(1, 2).flatMap(x -> Flux.range(x * 10, 100).take(x)).toStream().forEach(System.out::println);

//        Flux.just(1, 2).concatMap(x -> Flux.range(x * 10, 100).take(x)).toStream().forEach(System.out::println);

//        Flux.combineLatest(Arrays::toString, Flux.range(1, 50).take(10), Flux.range(50, 100).take(8)).subscribe(System.out::println);

//        Flux.just("1", 2).concatWith(Mono.error(new IllegalArgumentException()))
//                .onErrorReturn("0")
//                .onErrorResume(e -> {
//                    if (e instanceof IllegalStateException) {
//                        return Mono.just("0");
//                    } else if (e instanceof IllegalArgumentException) {
//                        return Mono.just("-1");
//                    }
//                    return Mono.empty();
//                })
//                .subscribe(System.out::println);

//        final Flux<Integer> flux = Flux.create(sink -> {
//            for (int i = 0; i < 1000; i++) {
//                sink.next(i);
//            }
//            sink.complete();
//        }, FluxSink.OverflowStrategy.BUFFER);
//        System.out.println(flux.count().subscribe(System.out::println));
//        flux.subscribe(e -> {
//            System.out.println(e);
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            }
//        });
    }

}
