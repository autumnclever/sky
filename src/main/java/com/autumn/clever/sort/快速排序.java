package com.autumn.clever.sort;

/**
 * 快速排序也采用了分治的策略，这里引入了‘基准数’的概念。
 * <p>
 * 1. 找一个基准数（一般将待排序的数组的第一个数作为基准数）
 * 2. 对数组进行分区，将小于等于基准数的全部放在左边，大于基准数的全部放在右边。
 * 3. 重复 1，2 步骤，分别对左右两个子分区进行分区，一直到各分区只有一个数为止。
 * <p>
 * 作者：Eran_promise
 * 链接：https://www.jianshu.com/p/fced9db5ff23
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2020/9/6 19:18
 */
public class 快速排序 {
    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 8, 4};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            // arr[left] 在 arr 中
            int index = sort(arr, left, right);
            quickSort(arr, 0, index - 1);
            quickSort(arr, index + 1, right);
        }
    }

    /**
     * 在 arr 中，把 arr[left] 作为基准数 temp，
     * 把小于 temp 的数字都放在 arr 数组中的左半部，
     * 把大于 temp 的数字都放在 arr 数组中的右半部，
     * 最后，把 temp 放在左边和右边的临街处，即 left 和 right 碰撞的位置上
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int sort(int[] arr, int left, int right) {
        // 将待排数组的第一个数字，作为基准
        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] > temp) {
                // 右边大于基准数的不动
                right--;
            }
            if (left < right) {
                // 把遇到的小于 temp 的数字放到左边，左边的起始位置是 left，即 temp 的位置
                arr[left++] = arr[right];
            }
            while (left < right && arr[left] < temp) {
                // 左边小于 temp 的数字不动
                left++;
            }
            if (left < right) {
                // 把遇到的大于 temp 的数字放到右边，右边的起始位置是 right，也是上一次跟 temp 交换位置的值的位置，
                // 因为已经与 left 位置的值进行了交换，所以该数字不会丢失
                arr[right--] = arr[left];
            }
        }
        // 因为 left 起始位置的值已经被覆盖，即 temp，最终 left 遍历到的截止位置是一个重复的值，用 temp 把它覆盖掉，
        // 这样从 left 起始位置到 temp 中间的值，全部都是小于 temp 的数，从 temp 中间的值开始到最右边的值都是大于 temp 的值
        arr[left] = temp;
        return left;
    }
}
