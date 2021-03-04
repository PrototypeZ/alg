package codingInterviews.q18;

import util.ListNode;
import util.Util;

/**
 *
 * 剑指 Offer 18. 删除链表的节点
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 *
 * 返回删除后的链表的头节点。
 *
 * 注意：此题对比原题有改动
 *
 * 示例 1:
 *
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 *
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 *
 * 说明：
 *
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 * 通过次数103,979提交次数176,167
 * 在真实的面试中遇到过这道题？
 *
 *
 * https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
 * Created by Jason on 2020/10/18/0018.
 */
public class Solution {

    /**
     * 主要难点在于要考虑被删除的节点是不是头结点
     * @param head
     * @param node
     * @return
     */
    public ListNode deleteNode(ListNode head, int node) {
        if (head == null) {
            return null;
        }
        if (head.val == node) {
            return head.next;
        }
        ListNode previous = head;
        ListNode current = head.next;
        while (current != null && current.val != node) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            // we find the node
            previous.next = current.next;
        }
        return head;
    }


    public ListNode deleteNode(ListNode head, ListNode node) {
        if (node == null) {
            return head;
        }
        if (head == null) {
            return null;
        }
        if (head == node) {
            return head.next;
        }
        ListNode previous = head;
        ListNode current = head.next;
        while (current != null && current != node) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            // we find the node
            previous.next = current.next;
        }
        return head;
    }

//    private ListNode deleteNode(ListNode head, ListNode node) {
//        if (node == null) return head;
//
//        if (node == head) {
//            ListNode nextNode = node.next;
//            if (nextNode == null) {
//                return null;
//            } else {
//                ListNode nextVal = node.next;
//                head.next = null;
//                return nextVal;
//            }
//        } else if (node.next == null) {
//            // ListNodes > 1 && node is tail
//            ListNode current = head;
//            while (current.next != node) {
//                current = current.next;
//            }
//            current.next = null;
//            return head;
//        } else {
//            // node is not head or tail
//            node.val = node.next.val;
//            ListNode nextNode = node.next;
//            node.next = nextNode.next;
//            nextNode.next = null;
//            return head;
//        }
//    }

    public static void main(String[] args) {

        Solution solution = new Solution();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        Util.printListNode(head); // 1 -> 2 -> 3 -> 4
        ListNode n1 = solution.deleteNode(head, head);
        Util.printListNode(n1); // 2 -> 3 -> 4
        ListNode n2 = solution.deleteNode(n1, n1.next);
        Util.printListNode(n2); // 2 -> 4
        ListNode n3 = solution.deleteNode(n2, n2.next);
        Util.printListNode(n3); // 2
        ListNode n4 = solution.deleteNode(n3, n3);
        System.out.println(n4); // null

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        Util.printListNode(head); // 1 -> 2 -> 3 -> 4
        n1 = solution.deleteNode(head, 1);
        Util.printListNode(n1); // 2 -> 3 -> 4
        n2 = solution.deleteNode(n1, 3);
        Util.printListNode(n2); // 2 -> 4
        n3 = solution.deleteNode(n2, 4);
        Util.printListNode(n3); // 2
        n4 = solution.deleteNode(n3, 2);
        System.out.println(n4); // null

    }
}
