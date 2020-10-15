package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2020/10/14/0014.
 */
public class Util {
    public static void printListNode(ListNode head) {
        ListNode node = head;
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(" -> ");
            }
            node = node.next;
        }
        System.out.println();
    }

    public static void printTreeNode(TreeNode root) {
        List<TreeNode> currentLevel = new ArrayList<>();
        List<TreeNode> nextLevel = new ArrayList<>();
        currentLevel.add(root);

        while (currentLevel.size() != 0) {
            boolean nextLevelAllNull = true;
            for (int i = 0; i < currentLevel.size(); i++) {
                TreeNode node = currentLevel.get(i);
                System.out.print(node == null ? "null" : String.valueOf(node.val));
                if (i != currentLevel.size() - 1) {
                    System.out.print(",");
                }

                if (node == null) {
                    nextLevel.add(null);
                    nextLevel.add(null);
                } else {
                    nextLevelAllNull = false;
                    nextLevel.add(node.left);
                    nextLevel.add(node.right);
                }
            }
            System.out.println();
            if (nextLevelAllNull) break;

            currentLevel.clear();
            currentLevel.addAll(nextLevel);
            nextLevel.clear();
        }
    }
}
