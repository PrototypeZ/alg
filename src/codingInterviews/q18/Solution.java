package codingInterviews.q18;

import util.ListNode;
import util.Util;

/**
 * Created by Jason on 2020/10/18/0018.
 */
public class Solution {

    private ListNode deleteNode(ListNode head, ListNode node) {
        if (node == null) return head;

        if (node == head) {
            ListNode nextNode = node.next;
            if (nextNode == null) {
                return null;
            } else {
                ListNode nextVal = node.next;
                head.next = null;
                return nextVal;
            }
        } else if (node.next == null) {
            // ListNodes > 1 && node is tail
            ListNode current = head;
            while (current.next != node) {
                current = current.next;
            }
            current.next = null;
            return head;
        } else {
            // node is not head or tail
            node.val = node.next.val;
            ListNode nextNode = node.next;
            node.next = nextNode.next;
            nextNode.next = null;
            return head;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        Util.printListNode(head);
        ListNode n1 = solution.deleteNode(head, head);
        Util.printListNode(n1);
        ListNode n2 = solution.deleteNode(n1, n1.next);
        Util.printListNode(n2);
        ListNode n3 = solution.deleteNode(n2, n2.next);
        Util.printListNode(n3);
        ListNode n4 = solution.deleteNode(n3, n3);
        System.out.println(n4);

    }
}
