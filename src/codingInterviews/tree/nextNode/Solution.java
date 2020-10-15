package codingInterviews.tree.nextNode;

import util.TreeNodeWithParent;

/**
 *
 * 给定一棵二叉树和其中的一个节点，如何找出中序遍历序列的下一个节点？
 * 树中除了有两个分别指向左右子节点的指针，还有一个指向父节点的指针
 * Created by Jason on 2020/10/15/0015.
 */
public class Solution {


    public TreeNodeWithParent findNextByInOrder(TreeNodeWithParent treeNode) {
        if (treeNode == null) {
            return null;
        }
        if (treeNode.right == null) {
            // 右子节点为空
            TreeNodeWithParent current = treeNode;

            while (current != null) {
                TreeNodeWithParent parent = current.parent;
                if (parent == null) {
                    return null;
                }
                if (parent.left == current) {
                    // 当前节点是父节点的左子节点
                    return parent;
                } else {
                    // 当前节点是父节点的右子节点
                    current = parent;
                }
            }
            return null;
        } else {
            TreeNodeWithParent current = treeNode.right;
            while (current.left != null){
                current = current.left;
            }
            return current;
        }
    }


    public static void main(String[] args) {
        TreeNodeWithParent root = new TreeNodeWithParent(1);

        root.left = new TreeNodeWithParent(2);
        root.left.parent = root;
        root.right = new TreeNodeWithParent(3);
        root.right.parent = root;

        root.left.left = new TreeNodeWithParent(4);
        root.left.left.parent = root.left;
        root.left.right = new TreeNodeWithParent(5);
        root.left.right.parent = root.left;
        root.right.left = new TreeNodeWithParent(6);
        root.right.left.parent = root.right;
        root.right.right = new TreeNodeWithParent(7);
        root.right.right.parent = root.right;

        root.left.right.left = new TreeNodeWithParent(8);
        root.left.right.left.parent = root.left.right;
        root.left.right.right = new TreeNodeWithParent(9);
        root.left.right.right.parent = root.left.right;


        Solution solution = new Solution();
        System.out.println(nodeValue(solution.findNextByInOrder(root)));//6

        System.out.println(nodeValue(solution.findNextByInOrder(root.left))); // 8
        System.out.println(nodeValue(solution.findNextByInOrder(root.right))); // 7

        System.out.println(nodeValue(solution.findNextByInOrder(root.left.left))); // 2
        System.out.println(nodeValue(solution.findNextByInOrder(root.left.right))); // 9
        System.out.println(nodeValue(solution.findNextByInOrder(root.right.left))); // 3
        System.out.println(nodeValue(solution.findNextByInOrder(root.right.right))); // null

        System.out.println(nodeValue(solution.findNextByInOrder(root.left.right.left)));// 5
        System.out.println(nodeValue(solution.findNextByInOrder(root.left.right.right)));// 1
    }

    private static String nodeValue(TreeNodeWithParent node) {
        if (node == null) {
            return "null";
        } else {
            return String.valueOf(node.val);
        }
    }
}
