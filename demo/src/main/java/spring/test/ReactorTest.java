package spring.test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: johnny.ly
 * Date: 2019/4/23
 * Time: 19:01
 * Desc:
 */
public class ReactorTest {
    public static void main(String[] args) {
        Mono.just(1).subscribe(System.out::println);

        Flux.just(1, 2, 3, 4, 5)
                .map(i -> 10 / (i - 3))
                .onErrorContinue((e, i) -> {
                    System.err.println(e.getMessage());
                    System.out.println("error " + i);
                })
                .subscribe(System.out::println, null, () -> {
                    long time = System.currentTimeMillis();
                    System.out.println("done " + time);
                });
    }
}
