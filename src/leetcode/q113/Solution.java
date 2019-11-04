package leetcode.q113;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

 说明: 叶子节点是指没有子节点的节点。

 示例:
 给定如下二叉树，以及目标和 sum = 22，

 5
 / \
 4   8
 /   / \
 11  13  4
 /  \    / \
 7    2  5   1
 返回:

 [
 [5,4,11,2],
 [5,8,4,5]
 ]


 * Created by Jason on 2019/11/4/0004.
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

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSumInternal(root, sum, new ArrayList<>(), result);
        return result;
    }


    public void pathSumInternal(TreeNode node, int sum, List<Integer> alreadyVisited, List<List<Integer>> result) {
//        if (sum == 0) {
//            result.add(alreadyVisited);
//        } else if (sum > 0) {
//            if (node != null) {
//                List<Integer> newAlreadyVisited = new ArrayList<>(alreadyVisited);
//                newAlreadyVisited.add(node.val);
//                pathSumInternal(node.left, sum - node.val, newAlreadyVisited, result);
//                pathSumInternal(node.right, sum - node.val, newAlreadyVisited, result);
//            }
//        }

        if (node == null) return;
        // 因为树的节点说了整数，没说正整数，所以在到达叶子前没法提前剪枝
        // 而且求的是从 root 到 leaf 的 path，如果在到达 leaf 前满足条件也没法提前终止
        if (sum == node.val && node.left == null && node.right == null) {
            List<Integer> newAlreadyVisited = new ArrayList<>(alreadyVisited);
            newAlreadyVisited.add(node.val);
            result.add(newAlreadyVisited);
        } else {
            List<Integer> newAlreadyVisited = new ArrayList<>(alreadyVisited);
            newAlreadyVisited.add(node.val);
            pathSumInternal(node.left, sum - node.val, newAlreadyVisited, result);
            pathSumInternal(node.right, sum - node.val, newAlreadyVisited, result);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode test = new TreeNode(5);
        test.left = new TreeNode(4);
        test.right = new TreeNode(8);
        test.left.left = new TreeNode(11);
        test.right.left = new TreeNode(13);
        test.right.right = new TreeNode(4);
        test.left.left.left = new TreeNode(7);
        test.left.left.right = new TreeNode(2);
        test.right.right.left = new TreeNode(5);
        test.right.right.right = new TreeNode(1);

        System.out.println(solution.pathSum(test, 22));

        TreeNode test2 = new TreeNode(1);
        test2.left = new TreeNode(2);

        System.out.println(solution.pathSum(test2, 1));

        TreeNode test3 = new TreeNode(-2);
        test3.right = new TreeNode(-3);

        System.out.println(solution.pathSum(test3, -5));

        /**
         *            1
         *        -2    -3
         *       1  3  -2  null
         *    -1
         */
        TreeNode test4 = new TreeNode(1);
        test4.left = new TreeNode(-2);
        test4.right = new TreeNode(-3);

        test4.left.left = new TreeNode(1);
        test4.left.right = new TreeNode(3);
        test4.right.left = new TreeNode(-2);

        test4.left.left.left = new TreeNode(-1);

        System.out.println(solution.pathSum(test4, -1));
    }
}
