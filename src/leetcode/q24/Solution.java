package leetcode.q24;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2019/10/17/0017.
 */
public class Solution {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private static void printListNode(ListNode head) {
        ListNode node = head;
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(" -> ");
            }
            node = node.next;
        }
        System.out.println();
    }


    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode o = dummyHead;
        ListNode p = o.next;
        ListNode q = o.next.next;

        while (p != null && q != null) {
            // change o -> p -> q to o -> q -> p
            o.next = q;
            p.next = q.next;
            q.next = p;

            o = p;
            p = o.next; // maybe null
            if (p != null) {
                q = p.next;
            } else {
                q = null;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode test = new ListNode(1);
        test.next = new ListNode(2);
        test.next.next = new ListNode(3);
        test.next.next.next = new ListNode(4);

        printListNode(solution.swapPairs(test));

        ListNode test1 = new ListNode(1);
        printListNode(solution.swapPairs(test1));

        ListNode test2 = new ListNode(1);
        test2.next = new ListNode(2);
        test2.next.next = new ListNode(3);
        printListNode(solution.swapPairs(test2));
    }
}
