package leetcode.q94;

import java.util.*;

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
        // current == null 的时候表示，current 的父节点的右子节点不存在
        // curr 为遍历指针，curr 的移动路径就是遍历的路径
        // 但是 curr 的值的变化又不完全时真正的遍历路径
        // 因为真正的中序遍历过程中， curr 不会指向 null 的，
        // 但是我们实现的中序遍历过程中，允许 curr 指向 null
        // 1.当栈为空，curr == null 时， 遍历结束
        // 2.当栈不为空， curr == null 时，我们弹出栈顶元素，使 curr 指向它，继续遍历
        // 当代码运行到这一行，此时栈不为空， curr == null 时，只有一种可能，curr 尝试指向某个节点的右子节点，
        // 但是这个右子节点其实不存在，换计划说，上面说的“某个节点”，以这个“某个节点”为根节点的子树已经遍历完毕（这一点后面也会讲到）
        while (curr != null || !stack.isEmpty()) {
            // 只要当前节点包含左子节点，current 就要向左子节点移动
            // 同时把当前的节点加入待遍历节点栈
            // 直到遍历到某个节点，它的左子节点为 null
            //
            // 这里也有一点 trick 在里面，凡是入栈的节点，出栈之后不能再走下面的 while 循环，
            // 不然循环永远无法退出
            // 也就是说顺序是这样的，curr 指针指向某个元素，查看这个元素是否为空
            // 如果不为空，压栈，curr 指向左子节点，再重复上述操作
            // 在代码到达这块 while 循环之前，如果 curr ！= null
            // 此时 curr 指向的是某个节点的右子节点
            // 也就是此时的 curr 的指向， 在后面 curr 赋值以前， curr 要么就是根节点，要么就是某个节点的右子节点
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // 走到这一步，此时当前节点必为 null，stack 里面至少有一个节点
            // 我们把这个 stack 里的最后一个节点取出来，很显然，这个节点肯定
            // 不包含左子节点，我们直接遍历它自身，然后扫描它的右子节点
            // 这句其实很妙，它包含两种情况：
            // 1. 当当前遍历的左节点存在时不停压栈，总能到达一个时机那就是某个时刻左子节点为null，但是根据上面的 while 语句
            // 此时 curr 已经为 null 了，只差没有压栈了，所以我们通过把现有栈弹出栈顶元素，相当于往后回退一步
            // 2. 当遍历某个节点右子节点时，而这个右子节点恰好为 null，根据中序遍历的原则，如果遍历某个右子节点时，这个节点为 null
            // 要么遍历应该结束（栈内为空），要么应该弹出栈顶元素继续遍历，显然能走到这里表示栈非空，所以应该弹出栈顶元素
            //
            // 为什么 `while (curr != null)` 块要放在 `curr = stack.pop();` 前面呢？
            // 其实这里也有巧妙的设计， 在 pop 之后，
            curr = stack.pop();
            // 这一步添加的这个元素其实是中序遍历中间那个节点
            res.add(curr.val);
            // 遍历完中间节点，需要指向右子节点
            // 如果右节点存在 curr.right != null, 那么下一轮循环继续尽可能入栈
            // 如果右节点不存在 curr.right == null， curr 就是叶子节点， 那么下一轮循环的时候需要从栈里取出一个节点
            curr = curr.right;
        }
        return res;
    }

    public List<Integer> inorderTraversalReadable(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currRoot = root;
        // currRoot 可能指向：
        // 1. 根节点
        // 2. 某个节点的右子节点
        // 如果根节点为 null 直接退出，如果某个节点的右子节点为空，那么那个节点不含右子树不存在，那个节点的遍历结束
        // 这时候看栈是否为空，若栈为空，程序退出；若栈不为空，出栈一个元素，此时这个刚刚出栈的元素的左子树已经遍历结束（思考为什么）
        // 那么这时候就应该遍历这个元素，然后遍历这个元素的右子树
        while (currRoot != null || !stack.isEmpty()) {
            if (currRoot != null) {
                while (currRoot.left != null) {
                    stack.push(currRoot);
                    currRoot = currRoot.left;
                }
                res.add(currRoot.val);
                currRoot = currRoot.right;
            } else {
                TreeNode rootInStack = stack.pop();
                res.add(rootInStack.val);
                currRoot = rootInStack.right;
            }
        }
        return res;
    }

    /**
     * 中序遍历一种更容易理解的方式，模拟递归的工作方式
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        /*
          node： 当前 node
          boolean： 当前 node 的左子树是否已经被遍历
         */
        Stack<Map.Entry<TreeNode, Boolean>> stack = new Stack<>();
        if (root != null) {
            stack.push(new AbstractMap.SimpleEntry<>(root, false));
        }
        while (!stack.empty()) {
            Map.Entry<TreeNode, Boolean> entry = stack.pop();
            boolean leftChildHasTraveled = entry.getValue();
            TreeNode node = entry.getKey();
            if (leftChildHasTraveled) {
                result.add(node.val);
            } else {
                if (node.right != null) {
                    stack.push(new AbstractMap.SimpleEntry<>(node.right, false));
                }
                stack.push(new AbstractMap.SimpleEntry<>(node, true));
                if (node.left != null) {
                    stack.push(new AbstractMap.SimpleEntry<>(node.left, false));
                }
            }
        }
        return result;
    }

    // 根据 inorderTraversal3 稍作修改
    public List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<Map.Entry<TreeNode, Boolean>> stack = new Stack<>();
        if (root != null) {
            stack.push(new AbstractMap.SimpleEntry<>(root, false));
        }
        while (!stack.empty()) {
            Map.Entry<TreeNode, Boolean> entry = stack.pop();
            boolean leftChildHasTraveled = entry.getValue();
            TreeNode node = entry.getKey();
            if (leftChildHasTraveled) {
                result.add(node.val);
                if (node.right != null) {
                    stack.push(new AbstractMap.SimpleEntry<>(node.right, false));
                }
            } else {
                stack.push(new AbstractMap.SimpleEntry<>(node, true));
                if (node.left != null) {
                    stack.push(new AbstractMap.SimpleEntry<>(node.left, false));
                }
            }
        }
        return result;
    }

    // 根据 inorderTraversal4 稍作修改
    public List<Integer> inorderTraversal5(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.empty()) {
            TreeNode node = stack.peek();

            while (node.left != null) {
                stack.push(node.left);
                node = node.left;
            }

            result.add(node.val);
            stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return result;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            // 到叶子节点了
            if (curr.left == null && curr.right == null) {
                res.add(curr.val);
                continue;
            }
            if (curr.left == null) {
                // 左子节点 null， 右子节点不为 null
                res.add(curr.val);
                stack.add(curr.right);
                continue;
            }
            // 左右子节点均不为 null
            while (curr.left != null) {
                stack.push(curr.left);
                curr = curr.left;
            }
            // 已经没有左子节点了，curr 自己就是左子节点
            res.add(curr.val);
            TreeNode parent = stack.pop();
            res.add(parent.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return res;
    }

    public void inorderTraversalRecursive(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorderTraversalRecursive(node.left, result);
        result.add(node.val);
        inorderTraversalRecursive(node.right, result);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode test = new TreeNode(1);
        test.right = new TreeNode(2);
        test.right.left = new TreeNode(3);
        System.out.println("inorderTraversal:" + solution.inorderTraversal(null));
        System.out.println("inorderTraversal2:" + solution.inorderTraversal2(test));
        System.out.println("inorderTraversal3:" + solution.inorderTraversal3(test));


        // [2,3,null,1]
        TreeNode test2 = new TreeNode(2);
        test2.left = new TreeNode(3);
        test2.left.left = new TreeNode(1);
        System.out.println("inorderTraversal:" + solution.inorderTraversal(test2));
        System.out.println("inorderTraversal3:" + solution.inorderTraversal3(test2));
        System.out.println("inorderTraversal5:" + solution.inorderTraversal5(test2));


    }
}
