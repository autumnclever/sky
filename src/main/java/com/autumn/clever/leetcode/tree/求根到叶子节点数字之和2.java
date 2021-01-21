package com.autumn.clever.leetcode.tree;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/16 上午10:28
 */
public class 求根到叶子节点数字之和2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        System.out.println(sumNumbers2(root));
    }

    public static int sumNumbers(TreeNode root) {
        List<String> numbers = new ArrayList<>();
        calculateNumbers(root, numbers, "");
        int sum = 0;
        if (CollectionUtils.isNotEmpty(numbers)) {
            for (String number : numbers) {
                Integer num = Integer.valueOf(number);
                sum += num;
            }
        }
        return sum;
    }

    public static void calculateNumbers(TreeNode root, List<String> numbers, String number) {
        if (root == null) {
            return;
        }
        number += root.val;
        if (root.left == null && root.right == null) {
            // 叶子结点添加到 list 当中
            numbers.add(number);
        } else {
            calculateNumbers(root.left, numbers, number);
            calculateNumbers(root.right, numbers, number);
            // 不要最后一个数字了
            number.substring(0, number.length() - 1);
        }
    }

    public static int sumNumbers2(TreeNode root) {
        return calculate(root, 0);
    }

    public static int calculate(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        // 每一层 sum * 10 + 当前根结点的值
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            // 如果是叶子结点，直接把 sum 返回
            return sum;
        }
        // 计算左孩子 + 计算右孩子，最终得到每一条路径的总和
        return calculate(root.left, sum) + calculate(root.right, sum);
    }

}
