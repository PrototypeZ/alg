package codingInterviews.q26;

import util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * �������ö�����A��B���ж�B�ǲ���A���ӽṹ��(Լ��������������һ�������ӽṹ)

 B��A���ӽṹ�� �� A���г��ֺ�B��ͬ�Ľṹ�ͽڵ�ֵ��

 ����:
 �������� A:

 ? ? ?3
 ? ? / \
 ? ?4 ? 5
 ? / \
 ?1 ? 2
 �������� B��

 ? ?4?
 ? /
 ?1
 ���� true����Ϊ B �� A ��һ������ӵ����ͬ�Ľṹ�ͽڵ�ֵ��

 ʾ�� 1��

 ���룺A = [1,2,3], B = [3,1]
 �����false
 ʾ�� 2��

 ���룺A = [3,4,5,1,2], B = [4,1]
 �����true
 ���ƣ�

 0 <= �ڵ���� <= 10000

 ��Դ�����ۣ�LeetCode��
 ���ӣ�https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
 ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
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
