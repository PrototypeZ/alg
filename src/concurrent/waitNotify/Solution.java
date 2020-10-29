package concurrent.waitNotify;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 例子中是个单生产者单消费者，
 * 但是实际应用中如果要应用这种写法，通常适合多生产者多消费者
 */
public class Solution {


    /**
     * 生产者线程
     * @author luzhanghong
     * @date 2018-06-09 14:23
     */
    static class Producer extends Thread {

        private final Queue<Integer> queue;
        private final int maxSize;
        private int messageCount = 1;

        public Producer(Queue<Integer> queue, int maxSize, String threadName) {
            super(threadName);
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {  // 在条件判断之前给共享资源加锁
                    while (queue.size() == maxSize) {
                        try {
                            System.out.println("消息队列已满: 生产者线程调用wait方法进入等待状态 ...");
                            queue.wait(); // 在循环体中：使用共享对象来调用wait方法，释放共享资源的锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        sleep(1000);  // 让生产者每1秒钟生产一条消息
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int messageId = messageCount++;
                    System.out.println("生产消息: " +  messageId);
                    queue.add(messageId);  // 将消息写入队列
                    queue.notifyAll();     // 通知消费者线程，对消息进行消费
                }
            }
        }
    }


    /**
     * 消费者线程
     * @author luzhanghong
     * @date 2018-06-09 14:37
     */
    static class Consumer extends Thread {

        private final Queue<Integer> queue;

        public Consumer(Queue<Integer> queue, String threadName) {
            super(threadName);
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {  // 在条件判断之前给共享资源加锁
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("消息队列为空: 消费者线程调用wait方法进入等待状态 ...");
                            queue.wait();  // 在循环体中：使用共享对象来调用wait方法，释放共享资源的锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("消费消息: " + queue.remove());
                    queue.notifyAll();     // 通知生产者线程，可以继续生产消息了
                }
            }
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize = 5;
        new Producer(queue, maxSize, "producer-thread").start();
        new Consumer(queue, "consumer-thread").start();
    }
}
