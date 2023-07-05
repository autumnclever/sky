package com.autumn.clever.baidu;

/**
 * @program: sky
 * @description: 优惠券计算
 * @author: zhangqiuying
 * @create: 2023-06-16 20:56
 **/
public class CouponCalculate {

    public static void main(String[] args) {
        Long fullPrice = 9999900L;
        Long salePrice = 9999800L;
        float ffullPrice = 9999900f;
        float fsalePrice = 9999800f;

        float saleRatio11 = (float) Math.round(fsalePrice) / ffullPrice;
        float saleRatio22 = (float) Math.round(fsalePrice * 100) / fullPrice / 100;
        float saleRatio33 = (float) fsalePrice / ffullPrice;
        float rate1 = Math.round((1.0f - saleRatio33) * 1000) / 1000;
        float saleRatio44 = fsalePrice / ffullPrice;
        float saleRatio55 = 1 - fsalePrice / ffullPrice;
        float rate2 = (float) Math.round(saleRatio55 * 10000000) / 10000000;
        float rate3 = (float) Math.round(saleRatio55 * 10000) / 10000;
        boolean isCorrectRate = rate3 < 0.1f;
        float saleRatio = ((float) (fullPrice - salePrice) / fullPrice);
        float fsaleRatio = (float) Math.round(saleRatio * 10000) / 10000;
        System.out.println(saleRatio11);
        System.out.println(saleRatio22);
        System.out.println(saleRatio33);
        System.out.println(saleRatio44);
        System.out.println(rate1);
        System.out.println(saleRatio55);
        System.out.println("rate2=" + rate2);
        System.out.println("rate3=" + rate3);
        System.out.println(isCorrectRate);
        System.out.println("saleRatio=" + saleRatio);
        System.out.println("fsaleRatio=" + fsaleRatio);

        float saleRatio1 = (float) Math.round(salePrice) / fullPrice;
        float saleRatio2 = (float) Math.round(salePrice * 100) / fullPrice / 100;
        float saleRatio3 = (float) salePrice / fullPrice;
        float rate = Math.round((1.0f - saleRatio3) * 1000000);
        float saleRatio4 = salePrice / fullPrice;
        System.out.println(saleRatio1);
        System.out.println(saleRatio2);
        System.out.println(saleRatio3);
        System.out.println(saleRatio4);
        System.out.println(rate);
    }
}
