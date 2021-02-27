package com.autumn.clever.leetcode.array;

import java.util.LinkedList;

/**
 * bfs: 宽度优先搜索
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/16 下午12:17
 */
public class 岛屿数量 {
    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j, row, col);
                    count++;
                }
            }
        }
        return count;
    }

    public static void bfs(char[][] grid, int i, int j, int row, int col) {
        LinkedList<Integer> list = new LinkedList();
        list.add(i * col + j);
        while (!list.isEmpty()) {
            int id = list.remove();
            int r = id / col;
            int c = id % col;
            if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                list.add((r - 1) * col + c);
                grid[r - 1][c] = '0';
            }
            if (r + 1 < row && grid[r + 1][c] == '1') {
                list.add((r + 1) * col + c);
                grid[r + 1][c] = '0';
            }
            if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                list.add((r * col + c - 1));
                grid[r][c - 1] = '0';
            }
            if (c + 1 < col && grid[r][c + 1] == '1') {
                list.add((r * col + c + 1));
                grid[r][c + 1] = '0';
            }
        }
    }
}
