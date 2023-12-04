package com.autumn.clever.baidu.grapeTask推送收银台;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-11-03 16:59
 **/
public class 推送收银台数据测试 {

    public static void main(String[] args) {
        String str1 = getStatusBinaryString(ReturnCouponEnum.TAKE,0);
        System.out.println(str1);
        System.out.println(Integer.valueOf(str1, 2).toString());
        String str2 = getStatusBinaryString(ReturnCouponEnum.ORDER_FAIL,0);
        System.out.println(Integer.valueOf(str2, 2).toString());
    }

    public static String getStatusBinaryString(ReturnCouponEnum returnCouponEnum, Integer isDelete) {
        if (returnCouponEnum == ReturnCouponEnum.TAKING) {
            // 领取中
            return "0010" + isDelete;
        } else if (returnCouponEnum == ReturnCouponEnum.TAKE || returnCouponEnum == ReturnCouponEnum.ORDER_FAIL) {
            // 未使用
            return "0100" + isDelete;
        } else if (returnCouponEnum == ReturnCouponEnum.USE) {
            // 使用
            return "0101" + isDelete;
        } else if (returnCouponEnum == ReturnCouponEnum.FAIL) {
            // 领取失败
            return "0110" + isDelete;
        } else if (returnCouponEnum == ReturnCouponEnum.INVALID) {
            // 失效
            return "1100" + isDelete;
        }
        return null;
    }
}
