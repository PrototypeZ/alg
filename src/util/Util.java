package util;

/**
 * Created by Jason on 2020/10/14/0014.
 */
public class Util {
    public static void printListNode(ListNode head) {
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
}
