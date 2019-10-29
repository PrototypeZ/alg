package leetcode.q92;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
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


    public ListNode reverseBetween(ListNode head, int m, int n) {
        // counter 表示 cur 指向的节点是第几个
        int counter = 1;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        ListNode cur = head;
        ListNode tmp;
        ListNode nodeM = null;
        ListNode nodeBeforeM = null;

        // 如果要反转 m 到 n 个元素的顺序，操作需要从第 m + 1 个数开始操作
        // 所以要先把指针移动到 m + 1 的位置，如果有非法的 m 存在（太大），移动可能提前结束
        while (counter <= m && cur != null) {
            if (counter == m) {
                nodeBeforeM = prev;
                nodeM = cur;
            }
            prev = prev.next;
            cur = cur.next;
            counter ++;
        }

        // 如果 m 太大提前结束
        if (cur == null) return head;

        // 从第 m + 1 个数开始执行反转，可能由于 n 太大提前结束
        while (counter <= n && cur != null) {
            // 先把 cur 的后面一位缓存一下，便于后面把 cur 指针后移
            tmp = cur.next;
            // 反转 cur 和 prev 的指向，注意如果是第一次进入循环，这个操作不会改变 prev.next 的指向
            cur.next = prev;
            // cur 和 prev 指针同时后移
            prev = cur;
            cur = tmp;

            counter ++;
        }
        // 循环退出以后， cur 指向原链表第 n + 1 个节点（如果有）
        // 所以 m 节点 next 直接指向 cur
        if (nodeM != null) {
            nodeM.next = cur;
        }
        // 循环退出以后， prev 指向原链表第 n 个节点
        if (nodeBeforeM != null) {
            nodeBeforeM.next = prev;
        }
        return dummyHead.next;
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode test = new ListNode(1);
        test.next = new ListNode(2);
        test.next.next = new ListNode(3);
        test.next.next.next = new ListNode(4);
        test.next.next.next.next = new ListNode(5);
        solution.reverseBetween(test, 2, 4);

        printListNode(test);
    }
}
