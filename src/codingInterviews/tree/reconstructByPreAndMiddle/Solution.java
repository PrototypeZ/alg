package codingInterviews.tree.reconstructByPreAndMiddle;

import util.TreeNode;
import util.Util;

/**
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *  
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 *  
 *
 * 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * 通过次数114,613提交次数165,777
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 根据二叉树前序和中序遍历结果，重建二叉树
 * Created by Jason on 2020/10/15/0015.
 */
public class Solution {


    public TreeNode parse(int[] preResult, int[] middleResult) {
        return parseInternal(preResult, 0, preResult.length - 1, middleResult, 0, middleResult.length - 1);
    }

    /**
     * 重建一棵子树
     * @param preResult 整棵树前序遍历的结果
     * @param preStart 需要重建的子树，前序遍历子序列在整棵树前序遍历的结果中起始位置
     * @param preEnd 需要重建的子树，前序遍历子序列在整棵树前序遍历的结果中结束位置
     * @param middleResult 整棵树中序遍历的结果
     * @param middleStart 需要重建的子树，中序遍历子序列在整棵树中序遍历的结果中起始位置
     * @param middleEnd 需要重建的子树，中序遍历子序列在整棵树中序遍历的结果中结束位置
     * @return 重建好的子树的根节点
     */
    public TreeNode parseInternal(int[] preResult, int preStart, int preEnd, int[] middleResult, int middleStart, int middleEnd) {
        int rootVal = preResult[preStart];
        TreeNode root = new TreeNode(preResult[preStart]);
        int rootIndexInMiddleResult = -1;
        for (int i = middleStart; i <= middleEnd; i++) {
            if (middleResult[i] == rootVal) {
                rootIndexInMiddleResult = i;
                break;
            }
        }
        int leftChildTreeSize = rootIndexInMiddleResult - middleStart;
        int rightChildTreeSize = middleEnd - rootIndexInMiddleResult;
        if (leftChildTreeSize > 0) {
            root.left = parseInternal(
                    preResult, preStart + 1, preStart + leftChildTreeSize,
                    middleResult, middleStart, rootIndexInMiddleResult - 1
            );
        }
        if (rightChildTreeSize > 0) {
            root.right = parseInternal(
                    preResult, preStart + leftChildTreeSize + 1, preEnd,
                    middleResult, rootIndexInMiddleResult + 1, middleEnd
            );
        }
        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//
//        root.left.left = new TreeNode(4);
//        root.right.left = new TreeNode(5);
//        root.right.right = new TreeNode(6);
//
//        root.left.left.right = new TreeNode(7);
//        root.right.right.left = new TreeNode(8);

//        Util.printTreeNode(root);

        Util.printTreeNode(solution.parse(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6}));

    }
}
