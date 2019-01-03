package leetcode.tencent.autumn2018.q2;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);

        ListNode result = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            result.next = new ListNode(sum % 10);

            // move cursors to next position
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            result = result.next;
        }

        return dummyHead.next;
    }


    public static void main(String[] args) {
        ListNode x = new ListNode(2);
        ListNode xCursor = x;
        xCursor.next = new ListNode(4);
        xCursor = xCursor.next;
        xCursor.next = new ListNode(3);

        ListNode y = new ListNode(5);
        ListNode yCursor = y;
        yCursor.next = new ListNode(6);
        yCursor = yCursor.next;
        yCursor.next = new ListNode(4);

        ListNode result = new Solution().addTwoNumbers(x, y);
        printListNode(result);

        System.out.println();

        x = new ListNode(5);

        y = new ListNode(5);

        result = new Solution().addTwoNumbers(x, y);
        printListNode(result);

    }

    private static void printListNode(ListNode head) {
        ListNode current = head;

        while(current != null) {
            if (current != head) {
                System.out.print(" -> ");
            }
            System.out.print(current.val);
            current = current.next;
        }
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}