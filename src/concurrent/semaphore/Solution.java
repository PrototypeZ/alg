package concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Solution {


    /**
     * 要达到的效果，有 20 个线程，每个线程的任务都是打印一句话，然后退出。
     * 但是同时只能有 5 个线程在执行任务，剩下的线程要等待别的线程执行完，自己才可以执行。
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            Runnable run = () -> {
                try {
                    // 获取许可
                    semp.acquire();
                    System.out.println("Accessing: " + NO);
                    Thread.sleep((long) (Math.random() * 10000));
                    // 访问完后，释放 ，如果屏蔽下面的语句，则在控制台只能打印5条记录，之后线程一直阻塞
                    semp.release();
                    System.out.println("Existing: " + NO);
                } catch (InterruptedException e) {
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }
}
