package codingInterviews.q31;

import java.util.Stack;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *  
 *
 * 提示：
 *
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed 是 popped 的排列。
 * 注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     * 思路就是我新建一个栈，来模拟这个情况，能模拟出来，就说明可以，模拟不出来就不可以。
     *
     * 从 popped 入手，依次判断 popped 能否由 push 序列弹出而来
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null) {
            return false;
        }
        if (pushed.length == 0 && popped.length == 0) {
            return true;
        }
        if (pushed.length != popped.length) {
            return false;
        }
        // 新建一个栈来模拟出栈入栈的情况
        Stack<Integer> stack = new Stack<>();
        // 指向将要入栈的元素
        int pushedPointer = 0;
        // 指向将要出栈的元素
        int poppedPointer = 0;

        while (poppedPointer < popped.length) {
            // 指向
            int testPopped = popped[poppedPointer];
            // 注意：在模拟过程中，stack 可能存在多次为空的状态，不仅仅是刚开始的时候
            // 例如入栈{1,2,3} , 出栈{1,2,3}。（每次栈空才入栈）
            // 所以 while 中有 `stack.empty()` 的条件
            while (stack.empty() || stack.peek() != testPopped) {
                // 假如当前栈指向的元素和 testPopped 不一样，只能通过从 pushed 序列 push 元素进栈，
                // 然后继续判断(不能 pop，pop就直接无法模拟我们要测试的情况了)。如果直到把整个 pushed 序列
                // 都 push 进栈的过程中，都无法满足栈顶和 testPopped 一样，说明无法模拟
                if (pushedPointer <= pushed.length - 1) {
                    // 还能从 pushed 里面 push 进栈
                    stack.push(pushed[pushedPointer++]);
                } else {
                    // pushed 里面元素已经全 push 了，但是当前指向的 testPopped 元素还没验证，所以肯定无法模拟了
                    return false;
                }
            }
            // 成功模拟一次 popped 序列中的一个出栈元素， poppedPointer 向后移动一位，尝试模拟下一个出栈元素
            stack.pop();
            poppedPointer++;
        }
        // 所有 popped 元素都被模拟成功，说明满足条件，返回 true
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.validateStackSequences(
                new int[] {1,2,3,4,5},
                new int[] {4,5,3,2,1}
        ));

        System.out.println(solution.validateStackSequences(
                new int[] {1,2,3,4,5},
                new int[] {4,3,5,1,2}
        ));
    }
}
