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

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            pathSumInternal(root, sum, new LinkedList<>(), result);
        }
        return result;
    }

    private void pathSumInternal(TreeNode root, int sum, List<Integer> currentPath, List<List<Integer>> result) {
        int valueLeft = sum - root.val;
        if (root.left == null && root.right == null) {
            // leaf node
            if (valueLeft == 0) {
                // path found
                currentPath.add(root.val);
                result.add(new ArrayList<>(currentPath));
                currentPath.remove(currentPath.size() - 1);
            }
        } else {
            // not leaf node
            if (root.left != null) {
                currentPath.add(root.val);
                pathSumInternal(root.left, valueLeft, currentPath, result);
                currentPath.remove(currentPath.size() - 1);
            }
            if (root.right != null) {
                currentPath.add(root.val);
                pathSumInternal(root.right, valueLeft, currentPath, result);
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
