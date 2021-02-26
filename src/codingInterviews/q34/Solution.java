package codingInterviews.q34;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。

 ?

 示例:
 给定如下二叉树，以及目标和?sum = 22，

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
 ?

 提示：

 节点总数 <= 10000
 注意：本题与主站 113?题相同：https://leetcode-cn.com/problems/path-sum-ii/

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2020/10/25/0025.
 */
public class Solution {

    /**
     * 思路： 用回溯的方法去解决这个问题
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            pathSumInternal(root, sum, new Stack<>(), result);
        }
        return result;
    }

    /**
     * 给定一个根节点和一个整数，从该根节点到叶子节点是否存在一条路径，使这条路径上的和为前面的那个整数
     *
     * 需要注意一点，节点上的数字有正有负，所以累加的中间值可能在正数，0，负数之间徘徊
     * @param root 当前的根节点
     * @param sum 整数和
     * @param currentPath 在遍历当前根节点之前已经走过的路径
     * @param result 全局的结果
     */
    private void pathSumInternal(TreeNode root, int sum, Stack<Integer> currentPath, List<List<Integer>> result) {
        // 尝试把当前节点纳入测试路径
        currentPath.push(root.val);
        int valueLeft = sum - root.val;
        if (root.left == null && root.right == null) {
            // 当前节点已经是叶子节点，
            if (valueLeft == 0) {
                // 且 valueLeft 为 0 ,符合要求的路径已找到
                result.add(new ArrayList<>(currentPath));
            }
            // 如果 valueLeft != 0，说明当前路径不符要求，什么都不用做，等待最后当前节点从当前路径被弹出即可
        } else {
            // 非叶子节点
            if (root.left != null) {
                pathSumInternal(root.left, valueLeft, currentPath, result);
            }
            if (root.right != null) {
                pathSumInternal(root.right, valueLeft, currentPath, result);
            }
        }
        // 回溯，很重要的一点是，对 currentPath 必须无副作用，即函数执行一开始 currentPath 什么值，
        // 那么函数执行结束后 currentPath 还是什么值
        currentPath.pop();
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);

        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        Solution solution = new Solution();
        System.out.println(solution.pathSum(root, 22));//[[5, 4, 11, 2], [5, 8, 4, 5]]


        TreeNode root2 = new TreeNode(-2);
        root2.left = new TreeNode(-3);
        System.out.println(solution.pathSum(root2, -5));//[[-2, -3]]


        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(-2);
        root3.right = new TreeNode(-3);

        root3.left.left = new TreeNode(1);
        root3.left.right = new TreeNode(3);
        root3.right.left = new TreeNode(-2);
        root3.right.right = null;

        root3.left.left.left = new TreeNode(-1);
        System.out.println(solution.pathSum(root3, -1));//[[1, -2, 1, -1]]

    }
}
