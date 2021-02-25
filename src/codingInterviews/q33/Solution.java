package codingInterviews.q33;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 *  
 *
 * 参考以下这颗二叉搜索树：
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 *
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 *
 * 输入: [1,3,2,6,5]
 * 输出: true
 *  
 *
 * 提示：
 *
 * 数组长度 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    /**
     * 验证某个后序遍历序列是否为二叉搜索树
     * @param postOrder 数据源
     * @param start 后续遍历数组在数据源中的起始下标
     * @param end 后序遍历数组在数据源中的结束下标
     * @param max 这个后续遍历数组中元素必须小于等于这个 max
     * @param min 这个后序遍历数组中的元素必须大于等于这个 min
     *
     * @return
     */
    private boolean verifyPostOrderInternal(int[] postOrder, int start, int end, int max, int min) {
        if (start == end) {
            return postOrder[start] >= min && postOrder[start] <= max;
        } else {
            // 当前判断序列的根节点
            int root = postOrder[end];
            // 如果根节点不满足最大最小限制，直接不符合
            if (root > max || root < min) return false;
            // check from start ~ end - 1
            int leftChildRange = start;
            // 在 [start, end - 1] 这个范围内，寻找左子树的序列
            while (postOrder[leftChildRange] <= root && leftChildRange < end) {
                leftChildRange++;
            }
            // 当上面这个循环退出的时候，要么 leftChildRange 进入了右子树序列的范围，
            // 要么 leftChildRange 指向了当前序列的 root 节点（end）
            leftChildRange -= 1;
            int pointerTestRight = leftChildRange + 1;
            // 如果把左子树的序列找出来以后，还有右子树，右子树中的每个元素也要检查一下是否都大于当前 root
            while (pointerTestRight < end) {
                if (postOrder[pointerTestRight++] < root) {
                    // 如果存在右子树节点小于 root，直接不符合
                    return false;
                }
            }

            if (leftChildRange >= start) {
                boolean leftResult = verifyPostOrderInternal(postOrder, start, leftChildRange, root - 1, min);
                if (!leftResult) return false;
            }
            if (leftChildRange + 1 < end) {
                boolean rightResult = verifyPostOrderInternal(postOrder, leftChildRange + 1, end - 1, max, root + 1);
                if (!rightResult) return false;
            }
            return true;
        }
    }

    /**
     * 二叉搜索树：
     * 1. 如果根节点的左子树存在，那么左子树上的所有节点均小于根节点的值，且左子树也是二叉搜索树；
     * 2. 如果根节点的右子树存在，那么右子树上所有节点均大于根节点的值，且右子树也是二叉搜索树；
     * 3. 二叉搜索树没有值相等的节点
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0) {
            return true;
        } else if (postorder.length == 1) {
            return true;
        } else {
            return verifyPostOrderInternal(postorder, 0, postorder.length - 1, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.verifyPostorder(new int[]{1, 6, 3, 2, 5})); // false
        System.out.println(solution.verifyPostorder(new int[]{1, 3, 2, 6, 5})); // true
        System.out.println(solution.verifyPostorder(new int[]{4, 8, 6, 12, 16, 14, 10})); //true
        System.out.println(solution.verifyPostorder(new int[]{})); //true
    }
}
