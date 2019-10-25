package leetcode.q86;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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

    public ListNode partition(ListNode head, int x) {
        ListNode greaterHead = new ListNode(0);
        ListNode lessHeader = new ListNode(0);
        ListNode pLess = lessHeader;
        ListNode pGreater = greaterHead;
        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                pLess.next = new ListNode(current.val);
                pLess = pLess.next;
            } else {
                pGreater.next = new ListNode(current.val);
                pGreater = pGreater.next;
            }
            current = current.next;
        }
        pLess.next = greaterHead.next;
        return lessHeader.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        printListNode(solution.partition(head, 3));

    }
}
