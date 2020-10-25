package codingInterviews.q33;

public class Solution {
    private boolean verifyPostOrderInternal(int[] postOrder, int start, int end, int max, int min) {
        if (start == end) {
            return postOrder[start] >= min && postOrder[start] <= max;
        } else {
            int root = postOrder[end];
            // check from start ~ end - 1
            int leftChildRange = start;
            while (postOrder[leftChildRange] <= root && leftChildRange < end) {
                leftChildRange++;
            }
            leftChildRange -= 1;
            int pointerTestRight = leftChildRange + 1;
            while (pointerTestRight < end) {
                if (postOrder[pointerTestRight++] < root) {
                    return false;
                }
            }

            if (leftChildRange >= start) {
                boolean leftResult = verifyPostOrderInternal(postOrder, start, leftChildRange, root, min);
                if (!leftResult) return false;
            }
            if (leftChildRange + 1 < end) {
                boolean rightResult = verifyPostOrderInternal(postOrder, leftChildRange + 1, end - 1, max, root);
                if (!rightResult) return false;
            }
            return true;
        }
    }


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
