package codingInterviews.q36;

/**
 *
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 *
 *  
 *
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 *
 *  
 *
 *
 *
 *  
 *
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 *
 *  
 *
 *
 *
 *  
 *
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 *
 *  
 *
 * 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 *
 * 注意：此题对比原题有改动。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node[] result = treeToDoublyListInternal(root);
        Node head = result[0];
        Node tail = result[1];
        tail.right = head;
        head.left = tail;
        return head;
    }

    /**
     * 给定一个二叉查找树的根节点，把这个二叉查找水变成双向链表，并且返回头节点和尾节点
     * 由二叉查找树的性质，左子树都比根节点小，右子树都比根节点大，
     * 所以最后的链表，左子树的所有节点肯定在根节点左边，右子树所有节点肯定在根节点右边，
     * 而左子树和右子树也是二叉查找树，所以可以递归执行上面的逻辑
     * @param node 根节点
     * @return
     */
    private Node[] treeToDoublyListInternal(Node node) {

        Node head, tail;

        if (node.left != null) {
            Node[] result = treeToDoublyListInternal(node.left);
            Node leftHead = result[0];
            Node leftTail = result[1];
            leftTail.right = node;
            node.left = leftTail;
            head = leftHead;
        } else {
            head = node;
        }

        if (node.right != null) {
            Node[] result = treeToDoublyListInternal(node.right);
            Node rightHead = result[0];
            Node rightTail = result[1];
            node.right = rightHead;
            rightHead.left = node;
            tail = rightTail;
        } else {
            tail = node;
        }

        return new Node[]{head, tail};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(5);

        root.left.left = new Node(1);
        root.left.right = new Node(3);

        Node head = solution.treeToDoublyList(root);
        Node current = head;
        do {
            System.out.printf("%s <- (%s)-> %s%n", current.left.val, current.val, current.right.val);
            current = current.right;
        } while (current != head);
    }
}
