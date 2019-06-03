package spring.test;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * User: johnny.ly
 * Date: 2019/4/23
 * Time: 14:30
 * Desc:
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Stopwatch stopWatch = Stopwatch.createStarted();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("first run" + Thread.currentThread().getName() + " " + stopWatch.elapsed(TimeUnit.MILLISECONDS));
                Thread.sleep(1000);
                System.out.println("first run");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 10;
        }, executorService).thenApply(i -> {
            try {
                System.out.println("second run" + Thread.currentThread().getName() + " " + stopWatch.elapsed(TimeUnit.MILLISECONDS));
                Thread.sleep(1000);
                System.out.println("second run");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return i + 1;
        }).thenApplyAsync(i -> {
            try {
                System.out.println("third run" + Thread.currentThread().getName() + " " + stopWatch.elapsed(TimeUnit.MILLISECONDS));
                Thread.sleep(1000);
                System.out.println("third run");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return i + 1;
        }, executorService);
        System.out.println(stopWatch.elapsed(TimeUnit.MILLISECONDS));
        System.out.println(completableFuture.get());
        System.out.println(stopWatch.elapsed(TimeUnit.MILLISECONDS));

        BiConsumer<String,Throwable> consumer = (str, e) -> {
            if (e != null) {
                e.printStackTrace();
                return;
            }
            System.out.println(str);
        };
        Map<Integer,String> valMap = Maps.newHashMap();
        valMap.put(1, "test");
        CompletableFuture.completedFuture(1)
                .thenApply(valMap::get)
                .whenComplete(consumer)
                .whenComplete(consumer);

        Stopwatch show = Stopwatch.createStarted();
        Function<Integer,Long> run = (i) -> {
            long time = 0L;
            if(i%10 == 3) {
                time = 3000;
            }else if(i%10 == 5) {
                time = 5000;
            }else{
                time = 1000;
            }
            try {
                System.out.println("start" + Thread.currentThread().getName() + " " + show.elapsed(TimeUnit.MILLISECONDS));
                Thread.sleep(time);
                System.out.println("end" + Thread.currentThread().getName() + " " + show.elapsed(TimeUnit.MILLISECONDS));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return time;
        };


        List<Integer> ins = Lists.newArrayList(1,5,3,3,2,6,7,4);
        CompletableFuture<Long>[] futures = ins.stream()
                .map(i -> CompletableFuture.completedFuture(i)
                        .thenApplyAsync(run,executorService))
                .toArray(CompletableFuture[]::new);

        List<Long> times = CompletableFuture.allOf(futures)
                .thenApply(v -> Stream.of(futures)
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList()))
                .join();

        times.stream().forEach(System.out::println);

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
