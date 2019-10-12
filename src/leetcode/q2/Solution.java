package leetcode.q2;

/**
 * ��������?�ǿ� ������������ʾ�����Ǹ������������У����Ǹ��Ե�λ���ǰ���?����?�ķ�ʽ�洢�ģ��������ǵ�ÿ���ڵ�ֻ�ܴ洢?һλ?���֡�
 * <p>
 * ��������ǽ��������������������᷵��һ���µ���������ʾ���ǵĺ͡�
 * <p>
 * �����Լ���������� 0 ֮�⣬���������������� 0?��ͷ��
 * <p>
 * ʾ����
 * <p>
 * ���룺(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * �����7 -> 0 -> 8
 * ԭ��342 + 465 = 807
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
        // ��λ
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
