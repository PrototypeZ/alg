package leetcode.q102;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

 例如:
 给定二叉树: [3,9,20,null,null,15,7],

 3
 / \
 9  20
 /  \
 15   7
 返回其层次遍历结果：

 [
 [3],
 [9,20],
 [15,7]
 ]


 * Created by Jason on 2019/11/3/0003.
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

    /**
     * 迭代解法
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> currentLevel = root == null ? new ArrayList<>() : Collections.singletonList(root);
        int level = 0;
        while (currentLevel.size() > 0) {
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode nodeInCurrentLevel : currentLevel) {
                List<Integer> values;
                if (result.size() <= level) {
                    values = new ArrayList<>();
                    result.add(values);
                } else {
                    values = result.get(level);
                }
                values.add(nodeInCurrentLevel.val);
                if (nodeInCurrentLevel.left != null) {
                    nextLevel.add(nodeInCurrentLevel.left);
                }
                if (nodeInCurrentLevel.right != null) {
                    nextLevel.add(nodeInCurrentLevel.right);
                }
            }
            currentLevel = nextLevel;
            level++;
        }
        return result;
    }

    // 递归解法
    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

        // fulfil the current level
        levels.get(level).add(node.val);

        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        return levels;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode test = new TreeNode(3);

        test.left = new TreeNode(9);
        test.right = new TreeNode(20);

        test.right.left = new TreeNode(15);
        test.right.right = new TreeNode(7);

        System.out.println(solution.levelOrder(test));

        System.out.println(solution.levelOrder(null));
    }
}
