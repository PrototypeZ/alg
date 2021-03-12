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
                // 如果 stack2 是空的，把 stack1 中所有元素弹出并 push 到 stack2
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
            }
            // 当些个元素入栈一个stack，从这个stack弹出，再入栈新的一个栈，
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
