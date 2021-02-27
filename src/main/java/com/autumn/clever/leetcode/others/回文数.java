package com.autumn.clever.leetcode.others;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/26 上午9:32
 */
public class 回文数 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(101));
        System.out.println(isPalindrome(123321));
        System.out.println(isPalindrome(12321));
        System.out.println(isPalindrome(-101));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        char[] arr = String.valueOf(x).toCharArray();
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            if (right - left < 3) {
                return true;
            }
            left++;
            right--;
        }
        return true;
    }
}
