package com.autumn.clever.baidu.order;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2024-02-26 19:41
 **/
public class 风控标签 {

    public static void main(String[] args) {
        boolean result = checkTags(16, Lists.newArrayList(4, 5));
        System.out.println(result);
    }

    /**
     * 校验订单标签第几位是否打标
     *
     * @param tags  订单标签
     * @param index 标签位数
     * @return true 包含标签   false 不包含
     */
    public static boolean checkTags(Integer tags, List<Integer> index) {
        // 标签为空无需校验
        if (tags == null) {
            return false;
        }
        int temp;
        for (int i : index) {
            temp = tags;
            // 将标签值tags向右移index位，然后和1相与，如果结果等于1，则tags的第index位为1 否则为0
            if (((temp >> i) & 1) == 1) {
                return true;
            }
        }

        return false;
    }
}
