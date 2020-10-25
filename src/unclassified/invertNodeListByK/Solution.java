package unclassified.invertNodeListByK;

/**
 * ● 按K个一组翻转单链表，1->2->3->4->5  当 K = 3 时，应当返回: 3->2->1->5->4
 */
public class Solution {

    static class Node {
        Node next;
        int val;

        public Node (int val) {
            this.val = val;
        }
    }


    public Node invertNodeListByK(Node head, int k) {
        if (k <= 0) return null;
        if (k == 1) return head;
        if (head == null) return null;
        int count = 0;
        Node current = head;
        Node lastTail = null;
        Node result = null;
        Node currentHead = null;
        Node currentTail = null;
        while (current != null) {
            if (count % k == 0) {
                currentHead = current;
                if (current.next == null) {
                    lastTail.next = current;
                }
                current = current.next;
            } else if (count % k == k - 1 || current.next == null) {
                currentTail = current;
                Node savedCurrentNext = current.next;
                currentTail.next = null;
                Node invertedHead = invertNodeList(currentHead);
                if (lastTail != null) {
                    lastTail.next = invertedHead;
                } else {
                    result = invertedHead;
                }
                lastTail = currentHead;
                current = savedCurrentNext;
            } else {
                current = current.next;
            }
            count++;
        }

        return result;
    }

    public Node invertNodeList(Node head) {
        if (head == null) return null;
        if (head.next != null) {
            Node inverted = invertNodeList(head.next);
            Node tail = head.next;
            tail.next = head;
            head.next = null;
            return inverted;
        } else {
            return head;
        }
    }

    private static void printNodeList(Node head) {
        Node current = head;
        while(current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution main = new Solution();
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        printNodeList(head);
        head = main.invertNodeListByK(head, 3);
        printNodeList(head);
        head = main.invertNodeListByK(head, 1);
        printNodeList(head);
        head = main.invertNodeListByK(head, 2);
        printNodeList(head);
    }
}