package codingInterviews.reverse;

import util.ListNode;
import util.Util;

/**
 * Created by Jason on 2020/10/14/0014.
 */
public class Solution2 {


    public ListNode reverse(ListNode head) {
        // 边界情况处理
        if (head == null) {
            return null;
        }

        ListNode current = head.next;
        ListNode pre = head;
        head.next = null;

        while (current != null) {
            // 保存下一个节点，因为下面的操作会覆盖这个指针
            ListNode tmp = current.next;
            // 反转指针
            current.next = pre;
            // 为下一次循环准备
            pre = current;
            current = tmp;
        }
        // 最后循环退出的时候，current == null，所以反转后的头指针是 pre
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
