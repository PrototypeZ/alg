package codingInterviews.tree.nextNode;

import util.TreeNodeWithParent;

/**
 *
 * ����һ�ö����������е�һ���ڵ㣬����ҳ�����������е���һ���ڵ㣿
 * ���г����������ֱ�ָ�������ӽڵ��ָ�룬����һ��ָ�򸸽ڵ��ָ��
 * Created by Jason on 2020/10/15/0015.
 */
public class Solution {


    public TreeNodeWithParent findNextByInOrder(TreeNodeWithParent treeNode) {
        if (treeNode == null) {
            return null;
        }
        if (treeNode.right == null) {
            // ���ӽڵ�Ϊ��
            TreeNodeWithParent current = treeNode;

            while (current != null) {
                TreeNodeWithParent parent = current.parent;
                if (parent == null) {
                    return null;
                }
                if (parent.left == current) {
                    // ��ǰ�ڵ��Ǹ��ڵ�����ӽڵ�
                    return parent;
                } else {
                    // ��ǰ�ڵ��Ǹ��ڵ�����ӽڵ�
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
