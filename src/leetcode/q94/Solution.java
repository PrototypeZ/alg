package leetcode.q94;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // 待遍历的节点，先扫描到的节点并不一定先遍历，所以用 stack 存起来
        // stack 中存放的待遍历的点，都是在中序遍历中，中间那个点
        // 准备用遍历中间那个点的方式，遍历所有的点（包括叶子节点）
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        // 当前扫描到的节点为空，且没有任何待遍历的节点时，循环退出
        // current == null 的时候表示，current 的父节点的右子节点不存在
        while (curr != null || !stack.isEmpty()) {
            // 只要当前节点包含左子节点，current 就要向左子节点移动
            // 同时把当前的节点加入待遍历节点栈
            // 直到遍历到某个节点，它的左子节点为 null
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // 走到这一步，此时当前节点必为 null，stack 里面至少有一个节点
            // 我们把这个 stack 里的最后一个节点取出来，很显然，这个节点肯定
            // 不包含左子节点，我们直接遍历它自身，然后扫描它的右子节点
            curr = stack.pop();
            // 这一步添加的这个元素其实是中序遍历中间那个节点
            res.add(curr.val);
            // 遍历完中间节点，需要指向右子节点
            curr = curr.right;
        }
        return res;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            // 到叶子节点了
            if (curr.left == null && curr.right == null) {
                res.add(curr.val);
                continue;
            }
            if (curr.left == null) {
                // 左子节点 null， 右子节点不为 null
                res.add(curr.val);
                stack.add(curr.right);
                continue;
            }
            // 左右子节点均不为 null
            while (curr.left != null) {
                stack.push(curr.left);
                curr = curr.left;
            }
            // 已经没有左子节点了，curr 自己就是左子节点
            res.add(curr.val);
            TreeNode parent = stack.pop();
            res.add(parent.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return res;
    }

    public void inorderTraversalRecursive(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorderTraversalRecursive(node.left, result);
        result.add(node.val);
        inorderTraversalRecursive(node.right, result);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

//        TreeNode test = new TreeNode(1);
//        test.right = new TreeNode(2);
//        test.right.left = new TreeNode(3);
//        System.out.println(solution.inorderTraversal2(test));
//
//        System.out.println(solution.inorderTraversal(null));

        // [2,3,null,1]
        TreeNode test2 = new TreeNode(2);
        test2.left = new TreeNode(3);
        test2.left.left = new TreeNode(1);
        System.out.println(solution.inorderTraversal2(test2));


    }
}
