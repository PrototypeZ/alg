package codingInterviews.q35;


import java.util.HashMap;
import java.util.Map;

/**
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 这题的难点时，拷贝后的节点里的 random 指向的节点，也必须是拷贝后的节点，不能是原链表中的节点
 * 所以这是一次深拷贝
 */
public class Solution {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 如果没有 random 指针的存在，那么只要从头指针开始依次往后复制即可
     * 但是由于 random 指针的存在，在复制过程中，简单复制 random 指针，会导致 random 指针指向老的链表的节点
     * 我们需要让新链表中节点的 random 指针指向新链表中的节点。
     *
     * 我们构造一个 map， key 为老链表中的节点， value 为新链表中的节点即可
     * @param head 头指针
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Map<Node, Node> oldNewMap = new HashMap<>();
        Node newHead = new Node(head.val);
        oldNewMap.put(head, newHead);

        Node current = head;
        while (current != null) {
            Node newCurrent = oldNewMap.get(current);

            // 复制 next 指针
            if (current.next != null) {
                Node newNext = oldNewMap.get(current.next);
                if (newNext == null) {
                    newNext = new Node(current.next.val);
                    oldNewMap.put(current.next, newNext);
                }
                newCurrent.next = newNext;
            }

            // 复制 random 指针
            if (current.random != null) {
                Node newRandom = oldNewMap.get(current.random);
                if (newRandom == null) {
                    newRandom = new Node(current.random.val);
                    oldNewMap.put(current.random, newRandom);
                }
                newCurrent.random = newRandom;
            }

            current = current.next;
        }
        return newHead;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        Node node7 = new Node(7);
        Node node13 = new Node(13);
        Node node11 = new Node(11);
        Node node10 = new Node(10);
        Node node1 = new Node(1);

        node7.next = node13;
        node7.random = null;
        node13.next = node11;
        node13.random = node7;
        node11.next = node10;
        node11.random = node1;
        node10.next = node1;
        node10.random = node11;
        node1.next = null;
        node1.random = node7;

        printNodeList(node7);
        System.out.println("copy result:");
        printNodeList(solution.copyRandomList(node7));
    }

    private static void printNodeList(Node node) {
        Node current = node;
        while (current != null) {
            System.out.println(String.format(
                    "node:[%s, %s], next:[%s, %s], random:[%s, %s]",
                    current.hashCode(), current.val,
                    current.next == null ? "null" : current.next.hashCode(), current.next == null ? "null" : current.next.val,
                    current.random == null ? "null" : current.random.hashCode(), current.random == null ? "null" : current.random.val
            ));
            current = current.next;
        }
    }
}
