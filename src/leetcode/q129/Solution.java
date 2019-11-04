package leetcode.q129;

/**
 *
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。

 例如，从根到叶子节点路径 1->2->3 代表数字 123。

 计算从根到叶子节点生成的所有数字之和。

 说明: 叶子节点是指没有子节点的节点。

 示例 1:

 输入: [1,2,3]
 1
 / \
 2   3
 输出: 25
 解释:
 从根到叶子节点路径 1->2 代表数字 12.
 从根到叶子节点路径 1->3 代表数字 13.
 因此，数字总和 = 12 + 13 = 25.
 示例 2:

 输入: [4,9,0,5,1]
 4
 / \
 9   0
  / \
 5   1
 输出: 1026
 解释:
 从根到叶子节点路径 4->9->5 代表数字 495.
 从根到叶子节点路径 4->9->1 代表数字 491.
 从根到叶子节点路径 4->0 代表数字 40.
 因此，数字总和 = 495 + 491 + 40 = 1026.


 * Created by Jason on 2019/11/4/0004.
 */
public class Solution {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int sumNumbers(TreeNode root) {
        int[] sumHolder = new int[1];
        if (root != null) {
            sumNumber3Internal(root, 0, sumHolder);
        }
        return sumHolder[0];
    }

    /**
     * 就是普通的回溯的思路
     */
    private void sumNumber3Internal(TreeNode node, int currentAccumulated, int[] sumHolder) {
        if (node.left == null && node.right == null) {
            sumHolder[0] += currentAccumulated * 10 + node.val;
        } else {
            if (node.left != null) {
                sumNumber3Internal(node.left, currentAccumulated * 10 + node.val, sumHolder);
            }
            if (node.right != null) {
                sumNumber3Internal(node.right, currentAccumulated * 10 + node.val, sumHolder);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode test = new TreeNode(1);
        test.left = new TreeNode(2);
        test.right = new TreeNode(3);
        System.out.println(solution.sumNumbers(test));

        TreeNode test2 = new TreeNode(4);
        test2.left = new TreeNode(9);
        test2.right = new TreeNode(0);
        test2.left.left = new TreeNode(5);
        test2.left.right = new TreeNode(1);
        System.out.println(solution.sumNumbers(test2));
    }
}
