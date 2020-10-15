package codingInterviews.tree.reconstructByPreAndMiddle;

import util.TreeNode;
import util.Util;

/**
 *
 * 根据二叉树前序和中序遍历结果，重建二叉树
 * Created by Jason on 2020/10/15/0015.
 */
public class Solution {


    public TreeNode parse(int[] preResult, int[] middleResult) {
        return parseInternal(preResult, 0, preResult.length - 1, middleResult, 0, middleResult.length - 1);
    }

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
