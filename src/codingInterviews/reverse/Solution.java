package codingInterviews.reverse;

import util.ListNode;
import util.Util;

/**
 * 翻转一个单链表
 * Created by Jason on 2020/10/14/0014.
 */
public class Solution {


    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        // 当前 head 指向还是原来的头指针
        // 当前 head.next 还是指向原来头指针后面一个节点，但是这个节点现在已经是尾节点了
        // 所以尾结点的 next 指向 head
        head.next.next = head;
        // 当前的 head 已经成为尾结点了，所以 next 置为 null
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
