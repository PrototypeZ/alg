package codingInterviews.reverse;

import util.ListNode;
import util.Util;

/**
 * Created by Jason on 2020/10/14/0014.
 */
public class Solution {


    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        Util.printListNode(head);
        Util.printListNode(solution.reverse(head));
    }
}
