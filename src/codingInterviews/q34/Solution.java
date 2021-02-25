package codingInterviews.q34;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
            pathSumInternal(root, sum, new LinkedList<>(), result);
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
    private void pathSumInternal(TreeNode root, int sum, List<Integer> currentPath, List<List<Integer>> result) {
        int valueLeft = sum - root.val;
        if (root.left == null && root.right == null) {
            // leaf node 到达叶子节点了
            if (valueLeft == 0) {
                // path found
                currentPath.add(root.val);
                result.add(new ArrayList<>(currentPath));
                currentPath.remove(currentPath.size() - 1);
            }
            // else 分支省略，因为如果 valueLeft 不为 0 说明这条路是错的，不要走这条路
            // 同时也不要对 currentPath 进行修改
        } else {
            // not leaf node 非叶子节点
            if (root.left != null) {
                // 路径往左下前进一步
                currentPath.add(root.val);
                pathSumInternal(root.left, valueLeft, currentPath, result);
                // 回溯
                currentPath.remove(currentPath.size() - 1);
            }
            if (root.right != null) {
                // 路径往右下前进一步
                currentPath.add(root.val);
                pathSumInternal(root.right, valueLeft, currentPath, result);
                // 回溯
                currentPath.remove(currentPath.size() - 1);
            }
        }
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
        System.out.println(solution.pathSum(root, 22));


        TreeNode root2 = new TreeNode(-2);
        root2.left = new TreeNode(-3);
        System.out.println(solution.pathSum(root2, -5));


        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(-2);
        root3.right = new TreeNode(-3);

        root3.left.left = new TreeNode(1);
        root3.left.right = new TreeNode(3);
        root3.right.left = new TreeNode(-2);
        root3.right.right = null;

        root3.left.left.left = new TreeNode(-1);
        System.out.println(solution.pathSum(root3, -1));

    }
}
