package codingInterviews.q26;

import util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

 B是A的子结构， 即 A中有出现和B相同的结构和节点值。

 例如:
 给定的树 A:

 ? ? ?3
 ? ? / \
 ? ?4 ? 5
 ? / \
 ?1 ? 2
 给定的树 B：

 ? ?4?
 ? /
 ?1
 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

 示例 1：

 输入：A = [1,2,3], B = [3,1]
 输出：false
 示例 2：

 输入：A = [3,4,5,1,2], B = [4,1]
 输出：true
 限制：

 0 <= 节点个数 <= 10000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2020/10/25/0025.
 */
public class Solution {


    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        return preOrderTraversal(A, B);

    }

    private boolean isTreeAContainsTreeB(TreeNode treeA, TreeNode treeB) {
        if (treeB == null) return true;
        if (treeA == null) return false;
        if (treeA.val == treeB.val) {
            return isTreeAContainsTreeB(treeA.left, treeB.left) && isTreeAContainsTreeB(treeA.right, treeB.right);
        } else {
            return false;
        }
    }

    public boolean preOrderTraversal(TreeNode root, TreeNode pattern) {
        if (root.val == pattern.val) {
            boolean result = isTreeAContainsTreeB(root, pattern);
            if (result) return true;
        }
        if (root.left != null) {
            boolean result = preOrderTraversal(root.left, pattern);
            if (result) return true;
        }
        if (root.right != null) {
            boolean result = preOrderTraversal(root.right, pattern);
            if (result) return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode aRoot = new TreeNode(3);
        aRoot.left = new TreeNode(4);
        aRoot.right = new TreeNode(5);

        aRoot.left.left = new TreeNode(1);
        aRoot.left.right = new TreeNode(2);


        TreeNode bRoot = new TreeNode(4);
        bRoot.left = new TreeNode(1);

        System.out.println(solution.isSubStructure(aRoot, bRoot)); // true

        TreeNode aRoot1 = new TreeNode(1);
        aRoot1.left = new TreeNode(2);
        aRoot1.right = new TreeNode(3);

        TreeNode bRoot1 = new TreeNode(3);
        bRoot1.left = new TreeNode(1);

        System.out.println(solution.isSubStructure(aRoot1, bRoot1)); // false

        TreeNode aRoot2 = new TreeNode(3);
        aRoot2.left = new TreeNode(4);
        aRoot2.right = new TreeNode(5);

        aRoot2.left.left = new TreeNode(1);
        aRoot2.left.right = new TreeNode(2);

        TreeNode bRoot2 = new TreeNode(4);
        bRoot2.left = new TreeNode(1);

        System.out.println(solution.isSubStructure(aRoot2, bRoot2)); // true

        TreeNode aRoot3 = new TreeNode(10);
        aRoot3.left = new TreeNode(12);
        aRoot3.right = new TreeNode(6);

        aRoot3.left.left = new TreeNode(8);
        aRoot3.left.right = new TreeNode(3);
        aRoot3.right.left = new TreeNode(11);

        TreeNode bRoot3 = new TreeNode(10);
        bRoot3.left = new TreeNode(12);
        bRoot3.right = new TreeNode(6);

        bRoot3.left.left = new TreeNode(8);

        System.out.println(solution.isSubStructure(aRoot3, bRoot3)); // true
    }
}
