package codingInterviews.stackAndQueue;

import java.util.Stack;

/**
 * 用两个栈实现一个队列
 * Created by Jason on 2020/10/15/0015.
 */
public class Solution {

    static class Queue<T> {
        Stack<T> stack1 = new Stack<>();
        Stack<T> stack2 = new Stack<>();

        void appendTail(T element) {
            stack1.push(element);
        }

        T deleteHead() {
            if (stack2.isEmpty()) {
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }


    public static void main(String[] args) {

        Queue<String> queue = new Queue<>();
        queue.appendTail("a");
        queue.appendTail("b");
        queue.appendTail("c");
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        queue.appendTail("d");
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }
}
