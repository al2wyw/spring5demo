package spring.test;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: johnny.ly
 * Date: 2019/4/21
 * Time: 22:06
 * Desc: 没什么卵用, 就是一个工具辅助类
 */
public class CompletionServiceTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        completionService.submit(() -> {
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            return 10;
        });
        completionService.submit(() -> {
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
            return 20;
        });
        completionService.submit(() -> {
            try{
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
            return 30;
        });

        while (true) {
            try {
                Future<Integer> future = completionService.take();
                System.out.println(future.get());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
