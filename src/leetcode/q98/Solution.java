package leetcode.q98;

import java.util.Stack;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 */
public class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 值得注意的是，给定一个节点，并不是当这个节点的左子树都小于这个节点&&这个节点的右子树都
    // 大于这个节点时这个节点的检测就是通过的，因为这个节点可能是它的父节点的左子节点或者右子节点
    // 所以这个节点的左右子树除了受这个节点的制约以外，还受这个节点的父节点的制约，即这个节点
    // 的左子树可能存在一个“下界”（如果这个节点是它父节点的右子节点），
    // 它的右子数可能存在一个“上界”（如果这个节点时它父节点的左子节点）。
    public boolean isValidBST(TreeNode root) {
        return isValidBSTInternal(root, null, null);
    }

    private boolean isValidBSTInternal(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;

        if ((min == null || node.val > min) && (max == null || node.val < max)) {
            return isValidBSTInternal(node.left, min, node.val)
                    && isValidBSTInternal(node.right, node.val, max);
        } else {
            return false;
        }
    }

    // 中序遍历：左子树 -> 结点 -> 右子树 意味着对于二叉搜索树而言，每个元素都应该比下一个元素小。
    public boolean isValidBSTByInorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode test = new TreeNode(2);
        test.left = new TreeNode(1);
        test.right = new TreeNode(3);
        System.out.println(solution.isValidBST(test));

        TreeNode test2 = new TreeNode(5);
        test2.left = new TreeNode(1);
        test2.right = new TreeNode(4);
        test2.right.left = new TreeNode(3);
        test2.right.right = new TreeNode(6);
        System.out.println(solution.isValidBST(test2));

        //[2147483647]
        TreeNode test3 = new TreeNode(2);

        test3.left = new TreeNode(1);
        test3.right = new TreeNode(4);
        test3.left.left = new TreeNode(7);
        test3.left.right = new TreeNode(4);
        test3.right.left = new TreeNode(7);
        test3.right.right = new TreeNode(4);

        test3.right.left.left = new TreeNode(7);
        test3.right.left.right = new TreeNode(7);
        test3.right.right.left = new TreeNode(4);
        test3.right.right.right = new TreeNode(4);
        System.out.println(solution.isValidBST(test3));


        TreeNode test4 = new TreeNode(2147483647);
        System.out.println(solution.isValidBST(test4));
    }
}
