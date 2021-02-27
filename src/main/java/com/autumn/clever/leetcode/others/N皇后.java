package com.autumn.clever.leetcode.others;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/16 下午4:32
 */
public class N皇后 {
    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens2(4);
        for (List<String> list : lists) {
            for (String str : list) {
                System.out.println(str);
            }
            System.out.println("------");
        }
    }

    public static List<List<String>> solveNQueens2(int n) {
        if (n == 0) {
            new ArrayList<>();
        }
        List<List<String>> lists = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        // 某一列是否使用
        boolean[] cUsed = new boolean[n];
        // 主对角线是否使用
        boolean[] mainUser = new boolean[2 * n - 1];
        // 副对角线是否使用
        boolean[] subUser = new boolean[2 * n - 1];
        help(n, 0, cUsed, mainUser, subUser, lists, list);
        return lists;
    }

    public static void help(int n, int row, boolean[] cUsed, boolean[] mainUsed, boolean[] subUsed, List<List<String>> lists, LinkedList<Integer> list) {
        if (n == row) {
            List<String> str = convert(list, n);
            lists.add(str);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (cUsed[i] || mainUsed[row - i + n - 1] || subUsed[row + i]) {
                continue;
            }
            list.addLast(i);
            cUsed[i] = true;
            mainUsed[row - i + n - 1] = true;
            subUsed[row + i] = true;

            help(n, row + 1, cUsed, mainUsed, subUsed, lists, list);

            list.removeLast();
            cUsed[i] = false;
            mainUsed[row - i + n - 1] = false;
            subUsed[row + i] = false;
        }
    }

    public static List<String> convert(List<Integer> list, int n) {
        List<String> strs = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(".");
        }
        for (Integer num : list) {
            sb.replace(num, num + 1, "Q");
            strs.add(sb.toString());
            sb.replace(num, num + 1, ".");
        }
        return strs;
    }


    public static List<List<String>> solveNQueens(int n) {
        if (n == 0) {
            new ArrayList<>();
        }
        List<List<String>> lists = new ArrayList<>();
        List<String> list = new ArrayList<>();
        boolean[] rUsed = new boolean[n];
        boolean[] cUsed = new boolean[n];
        StringBuilder sb = new StringBuilder();
        solveNQueens(n, 0, rUsed, cUsed, lists, list, sb);
        return lists;
    }

    public static void solveNQueens(int n, int begin, boolean[] rUsed, boolean[] cUsed, List<List<String>> lists, List<String> list, StringBuilder sb) {
        if (n == 0) {
            list.add(sb.toString());
            if (list.size() == n) {
                lists.add(new ArrayList<>(list));
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rUsed[i] || cUsed[j]) {
                    sb.append(',');
                    continue;
                }
                n--;
                sb.append('Q');
                rUsed[i] = true;
                cUsed[j] = true;
                solveNQueens(n, begin, rUsed, cUsed, lists, list, sb);
                rUsed[i] = false;
                cUsed[j] = false;
                sb.deleteCharAt(sb.length() - 1);
                n++;
            }
        }
    }
}
