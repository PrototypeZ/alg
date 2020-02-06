package leetcode.q2;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0?开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * Created by Jason on 2019/10/11/0011.
 */
public class Solution {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            ListNode current = this;
            int sum = 0;
            int i = 0;
            do {
                sum += current.val * Math.pow(10, i++);
                current = current.next;
            } while (current != null);

            return String.valueOf(sum);
        }
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 进位
        boolean enter = false;
        ListNode result = new ListNode(0);
        ListNode pointer = result;

        ListNode l1Current = l1;
        ListNode l2Current = l2;

        while (l1Current != null || l2Current != null || enter) {
            int l1CurrentVal = 0;
            int l2CurrentVal = 0;
            if (l1Current != null) {
                l1CurrentVal = l1Current.val;
                l1Current = l1Current.next;
            }
            if (l2Current != null) {
                l2CurrentVal = l2Current.val;
                l2Current = l2Current.next;
            }
            int testVal = l1CurrentVal + l2CurrentVal + (enter ? 1 : 0);
            enter = testVal > 9;
            // testVal 的取值范围是 0 ~ 19, 19 的情况是 9+9+进位。
            pointer.next = new ListNode(testVal % 10);
            pointer = pointer.next;
        }

        return result.next;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode a = new ListNode(2);
        a.next = new ListNode(4);
        a.next.next = new ListNode(3);

        ListNode b = new ListNode(5);
        b.next = new ListNode(6);
        b.next.next = new ListNode(4);

        System.out.println(solution.addTwoNumbers(a, b));
    }
}
