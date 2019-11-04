package leetcode.q103;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jason on 2019/11/3/0003.
 */
public class Solution {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> currentLevel = root == null ? new ArrayList<>() : Collections.singletonList(root);
        int level = 0;
        while (currentLevel.size() > 0) {
            List<TreeNode> nextLevel = new ArrayList<>();
            boolean reverse = level % 2 == 1;
            for (TreeNode nodeInCurrentLevel : currentLevel) {
                List<Integer> values;
                if (result.size() <= level) {
                    values = new ArrayList<>();
                    result.add(values);
                } else {
                    values = result.get(level);
                }
                if (reverse) {
                    values.add(0, nodeInCurrentLevel.val);
                } else {
                    values.add(nodeInCurrentLevel.val);
                }
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode test = new TreeNode(3);

        test.left = new TreeNode(9);
        test.right = new TreeNode(20);

        test.right.left = new TreeNode(15);
        test.right.right = new TreeNode(7);

        System.out.println(solution.zigzagLevelOrder(test));

        System.out.println(solution.zigzagLevelOrder(null));

        TreeNode test2 = new TreeNode(1);

        test2.left = new TreeNode(2);
        test2.right = new TreeNode(3);

        test2.left.left = new TreeNode(4);
        test2.right.right = new TreeNode(5);

        System.out.println(solution.zigzagLevelOrder(test2));
    }

}
