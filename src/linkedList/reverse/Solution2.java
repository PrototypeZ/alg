package linkedList.reverse;

import util.ListNode;
import util.Util;

/**
 * Created by Jason on 2020/10/14/0014.
 */
public class Solution2 {


    public ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode current = head.next;
        ListNode pre = head;
        head.next = null;

        while (current != null) {
            ListNode tmp = current.next;
            current.next = pre;
            pre = current;
            current = tmp;
        }

        return pre;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        Util.printListNode(head);
        Util.printListNode(solution.reverse(head));
    }
}
