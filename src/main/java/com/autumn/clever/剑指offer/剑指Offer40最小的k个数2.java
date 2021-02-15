package com.autumn.clever.剑指offer;

import java.util.Arrays;

/**
 * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
 * 给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。
 * https://www.nowcoder.com/practice/e016ad9b7f0b45048c58a9f27ba618bf?tpId=188&tags=&title=&diffculty=0&judgeStatus=0&rp=1&tab=answerKey
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/14 下午6:37
 */
public class 剑指Offer40最小的k个数2 {
    public static void main(String[] args) {
        int[] array = {
                1332802, 1177178, 1514891, 871248, 753214,
                123866, 1615405, 328656, 1540395, 968891,
                1884022, 252932, 1034406, 1455178, 821713,
                486232, 860175, 1896237, 852300, 566715,
                1285209, 1845742, 883142, 259266, 520911,
                1844960, 218188, 1528217, 332380, 261485,
                1111670, 16920, 1249664, 1199799, 1959818,
                1546744, 1904944, 51047, 1176397, 190970,
                48715, 349690, 673887, 1648782, 1010556,
                1165786, 937247, 986578, 798663};
        int[] arraySort = {
                16920, 48715, 51047, 123866, 190970,
                218188, 252932, 259266, 261485, 328656,
                332380, 349690, 486232, 520911, 566715,
                673887, 753214, 798663, 821713, 852300,
                860175, 871248, 883142, 937247, 968891,
                986578, 1010556, 1034406, 1111670, 1165786,
                1176397, 1177178, 1199799, 1249664, 1285209,
                1332802, 1455178, 1514891, 1528217, 1540395,
                1546744, 1615405, 1648782, 1844960, 1845742,
                1884022, 1896237, 1904944, 1959818
        };
        int[] array1 = {1, 3, 5, 2, 2};
//        Arrays.sort(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println(findKth(array1, 5, 5));
        System.out.println(findKth(array, 5, 24));
    }

    public static int findKth(int[] a, int n, int K) {
        return sort4(a, 0, a.length - 1, K);
    }

    public static int sort4(int[] a, int left, int right, int K) {

        int index = getIndex(a, left, right);
        if (index == K - 1) {
            return a[index];
        } else if (index > K - 1) {
            return sort4(a, left, index - 1, K);
        } else {
            return sort4(a, index + 1, right, K);
        }
    }

    public static int sort3(int[] a, int left, int right, int k) {
        int index = left;
        if (left < right) {
            index = getIndex(a, left, right);
            if (index == k - 1) {
                return index;
            } else if (index > k - 1) {
                return sort3(a, left, index - 1, k);
            } else {
                return sort3(a, index + 1, right, k);
            }
        }
        return index;
    }

    public static void sort2(int[] a, int left, int right, int k) {
        if (left < right) {
            int index = getIndex(a, left, right);
            if (index == k) {
                return;
            }
            sort(a, left, index - 1, k);
            sort(a, index + 1, right, k);
        }
    }

    public static void sort(int[] a, int left, int right, int k) {
        if (left < right) {
            int index = getIndex(a, left, right);
            sort(a, left, index - 1, k);
            if (index >= k) {
                return;
            }
            sort(a, index + 1, right, k);
        }
    }

    public static int getIndex(int[] a, int left, int right) {
        int temp = a[left];
        while (left < right) {
            while (left < right && a[right] > temp) {
                right--;
            }
            if (left < right) {
                a[left++] = a[right];
            }
            while (left < right && a[left] < temp) {
                left++;
            }
            if (left < right) {
                a[right--] = a[left];
            }
        }
        a[left] = temp;
        return left;
    }
}
