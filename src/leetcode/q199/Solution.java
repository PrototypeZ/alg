package leetcode.q199;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * }
 */
class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            List<TreeNode> nodesInCurrentLevel = new ArrayList<>();
            while (!queue.isEmpty()) {
                nodesInCurrentLevel.add(queue.poll());
            }
            TreeNode node = null;
            for (TreeNode n : nodesInCurrentLevel) {
                node = n;
                if (n.left != null) queue.add(n.left);
                if (n.right != null) queue.add(n.right);
            }
            if (node != null) {
                result.add(node.val);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);
        t1.left.right = new TreeNode(5);
        t1.right.right = new TreeNode(4);
        System.out.println(solution.rightSideView(t1));
    }
}