package com.autumn.clever.leetcode;

/**
 * 由 n 个连接的字符串 s 组成字符串 S，记作 S = [s,n]。例如，["abc",3]=“abcabcabc”。
 * <p>
 * 如果我们可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。例如，根据定义，"abc" 可以从 “abdbec” 获得，但不能从 “acbbe” 获得。
 * <p>
 * 现在给你两个非空字符串 s1 和 s2（每个最多 100 个字符长）和两个整数 0 ≤ n1 ≤ 106 和 1 ≤ n2 ≤ 106。现在考虑字符串 S1 和 S2，其中 S1=[s1,n1] 、S2=[s2,n2] 。
 * <p>
 * 请你找出一个可以满足使[S2,M] 从 S1 获得的最大整数 M 。
 *
 * @Author: zhangqiuying
 * @Date: 2020/8/12 20:18
 */
public class One {
    public static void main(String[] args) {
        String s1 = "abc";
        int n1 = 4;

        // abcde abcde abcde abcde abcde
        //   c   a   e   c   a   e

        String s2 = "ab";
        int n2 = 2;

        int res = getMaxRepetitions(s1, n1, s2, n2);
        int res2 = getMaxRepetitions2(s1, n1, s2, n2);
        System.out.println(res);
        System.out.println(res2);
    }

    public static int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 == 0 || len2 == 0 || n1 == 0 || n2 == 0) {
            return 0;
        }

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        // 记录下一个要匹配的 s2 中字符的下标
        int index2 = 0;
        // s2 的循环次数
        int loopTimeOnS2 = 0;

        // 当 s1 循环了 i 次以后，s2 的循环次数
        int[] times = new int[n1];
        // 当 s1 循环了 i 次以后，s2 下一个字符匹配的下标（上一次字符匹配完成以后，index2++，所以这里记录下一个匹配的下标）
        int[] next = new int[n1];

        for (int i = 0; i < n1; i++) {
            // 循环 s2
            for (int index1 = 0; index1 < len1; index1++) {

                // 如果匹配到 s2 中的字符
                if (chars1[index1] == chars2[index2]) {
                    index2++;
                }

                // 匹配完一个 s2，计数器 + 1，重置 s2 下标
                if (index2 == len2) {
                    // 循环扫描，因此 index2 重置为 0
                    index2 = 0;
                    loopTimeOnS2++;
                }
            }

            // 记录了 s1 遍历了 i 次（从 0 开始）以后，s2 已经完整地遍历了多少次
            times[i] = loopTimeOnS2;
            // 记录了 s1 遍历了 i 次（从 0 开始）以后，s2 上 index2 的位置（由于匹配完成以后执行了 index2++ ，此时 index2 指向下一个要匹配的字符）
            next[i] = index2;

            if (i > 0 && index2 == next[0]) {
                // 说明出现了循环节，直接计算结果，来自 3 个部分
                // 第 1 部分：开头
                int head = times[0];

                // 第 2 部分：中间循环部分
                // 这里难理解的话，一定要结合定义，看图理解，
                // (n1 - 1)，要减去 1 个，因为从 s1 已经循环了一次开始，才出现的循环节
                // (n1 - 1) / i 表示剩下的部分有多少个红色大括号段（见图）
                // times[i] - times[0] 表示：每个循环节里出现了几个 s2
                // ((n1 - 1) / i) * (times[i] - times[0]) 就表示中间那部分里面 s2 出现的次数
                int circulateCount = ((n1 - 1) / i) * (times[i] - times[0]);

                // 第 3 部分：结尾部分
                // (n1 - 1) % i 相对于 (n1 - 1) / i 而言，就是不能整除的部分
                // 减去 times[0] 是因为计算 times[i] 的时候计算的是前缀和，headCount 这部分已经计算过了，要把它删掉（写成两部分的和也可以）
                int endCount = times[(n1 - 1) % i] - times[0];

                // 总结：一下子计算出 loopTimesOnS2，是这个解法优化的地方
                return (head + circulateCount + endCount) / n2;
            }
        }
        // 走到这里，没有发现循环节，依据定义计算，n1 - 1 是最后一次遍历 s1 的下标
        return times[n1 - 1] / n2;
    }

    public static int getMaxRepetitions2(String s1, int n1, String s2, int n2) {

        int len1 = s1.length();
        int len2 = s2.length();
        // 特判
        if (len1 == 0 || len2 == 0 || n1 == 0 || n2 == 0) {
            return 0;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        // 记录下一个要匹配的 s2 中字符的下标
        int index2 = 0;

        // 记录 s2 循环的次数，loopTimesOnS2 是 index2 的函数
        int loopTimesOnS2 = 0;

        // 当 s1 循环了 i 次以后，s2 循环了几次
        int[] times = new int[n1];
        // 当 s1 循环了 i 次以后，s2 下一个字符匹配的下标（上一个字符匹配完以后 index2++，所以这里是下一个）
        int[] next = new int[n1];

        // 暴力解法的优化，一边遍历，一边找循环节，找到就直接计算出结果
        for (int i = 0; i < n1; i++) {

            // 指针 index1 在 s1 上循环游走
            for (int index1 = 0; index1 < len1; index1++) {

                // 如果匹配到 s2 中字符的字符
                if (chars1[index1] == chars2[index2]) {
                    index2++;
                }

                // 匹配完一个 s2，计数器 + 1，重置 s2 下标
                if (index2 == len2) {
                    // 循环扫描，因此 index2 重置为 0
                    index2 = 0;
                    loopTimesOnS2++;
                }
            }

            // 记录了 s1 遍历了 i 次（从 0 开始）以后，s2 已经完整地遍历了多少次
            times[i] = loopTimesOnS2;
            // 记录了 s1 遍历了 i 次（从 0 开始）以后，s2 上 index2 的位置（由于匹配完成以后执行了 index2++ ，此时 index2 指向下一个要匹配的字符）
            next[i] = index2;

            // 循环节一定是在：s1 至少循环了一次以后
            // s2 即将匹配的字符的下标恰好等于 s1 刚刚循环完一次以后 s2 即将要匹配的下标，就是 index2 == next[0] 这行代码的含义

            // 下面的代码如果不好理解的话，一定要结合 next、times、i、n1 的定义

            if (i > 0 && index2 == next[0]) {
                // 说明出现了循环节，直接计算结果，来自 3 个部分
                // 第 1 部分：开头
                int headCount = times[0];

                // 第 2 部分：中间循环部分
                // 这里难理解的话，一定要结合定义，看图理解，
                // (n1 - 1)，要减去 1 个，因为从 s1 已经循环了一次开始，才出现的循环节
                // (n1 - 1) / i 表示剩下的部分有多少个红色大括号段（见图）
                // times[i] - times[0] 表示：每个循环节里出现了几个 s2
                // ((n1 - 1) / i) * (times[i] - times[0]) 就表示中间那部分里面 s2 出现的次数
                int circulateCount = ((n1 - 1) / i) * (times[i] - times[0]);

                // 第 3 部分：结尾部分
                // (n1 - 1) % i 相对于 (n1 - 1) / i 而言，就是不能整除的部分
                // 减去 times[0] 是因为计算 times[i] 的时候计算的是前缀和，headCount 这部分已经计算过了，要把它删掉（写成两部分的和也可以）
                int endCount = times[(n1 - 1) % i] - times[0];

                // 总结：一下子计算出 loopTimesOnS2，是这个解法优化的地方
                return (headCount + circulateCount + endCount) / n2;
            }
        }
        // 走到这里，没有发现循环节，依据定义计算，n1 - 1 是最后一次遍历 s1 的下标
        return times[n1 - 1] / n2;
    }
}
