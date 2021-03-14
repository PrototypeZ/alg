package codingInterviews.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列模拟一个栈
 * Created by Jason on 2020/10/15/0015.
 */
public class Solution2 {
    class MyStack<T> {
        Queue<T> queue1;
        Queue<T> queue2;

        /** Initialize your data structure here. */
        public MyStack() {
            queue1 = new LinkedList<T>();
            queue2 = new LinkedList<T>();
        }

        /** Push element x onto stack.
         * 往 queue2 入队一个元素，然后如果 queue1 中有元素，全部入队到 queue2 中
         * 最后的结果，queue1 中一定是空的，queue2 中队伍最前面的是刚刚入队的元素
         * 再最后交换 queue1 和 queue2 的指针
         * 也就是最后 queue2 一定是空的，queue1 中是有元素的，且队伍头部是刚刚入队的元素
         *
         * 每次 push 操作完成之后的状态都是， queue1 中从队伍头到尾，依次是 push 进元素的倒序
         * 后进来的元素反而在队列的前部； 而 queue2 是空的。
         *
         * 根据 push 的原理和 push 操作完成后的状态， 队列的 pop 和 top 方法都和 queue1 有关
         * * */
        public void push(T x) {

            queue2.offer(x);
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }

            Queue<T> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        /** Removes the element on top of the stack and returns that element. */
        public T pop() {
            return queue1.poll();
        }

        /** Get the top element. */
        public T top() {
            return queue1.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }
}
