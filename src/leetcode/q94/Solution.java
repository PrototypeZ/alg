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

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode test = new TreeNode(1);
        test.right = new TreeNode(2);
        test.right.left = new TreeNode(3);
        System.out.println(solution.inorderTraversal(test));
    }
}
