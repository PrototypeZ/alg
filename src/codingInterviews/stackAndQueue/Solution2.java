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

        /** Push element x onto stack. */
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
