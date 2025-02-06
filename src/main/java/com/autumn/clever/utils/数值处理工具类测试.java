package com.autumn.clever.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class 数值处理工具类测试 {

    public static void main(String[] args) {
        float f1 = 12.12f;

//        BigDecimal bigDecimal = new BigDecimal(f1);
//        BigDecimal hundred = BigDecimal.valueOf(100);
//
//        System.out.println(f1);
//        f1 = bigDecimal.divide(hundred, 7, RoundingMode.HALF_UP).floatValue();
//        System.out.println(f1);
//
//        float f2 = 12.125555f;
//        System.out.println(f2);
//        f2 = new BigDecimal(f2).divide(hundred, 7, RoundingMode.HALF_UP).floatValue();
//        System.out.println(f2);
//
//        float f3 = 12.126666f;
//        System.out.println(f3);
//        f3 = new BigDecimal(f3).divide(hundred, 7, RoundingMode.HALF_UP).floatValue();
//        System.out.println(f3);

//        Integer index = 2;
//        System.out.println(index);
//        checkInteger(index);
//        System.out.println(index);

//        float f3 = chengJi(1.234323523452345f, 1.234567890123456f);
//        System.out.println(f3);
//        float f4 = chengJi(1.2341111111111f, 1.2341111110111111f);
//        System.out.println(f4);
//        float f5 = chengJi(1.2341111111111f, 1.2341111110111111f);
//        System.out.println(f5);
//
//        float f6 = chengJi(1.3f, 0.1300f);
//        System.out.println(f6);
//
//        float f7 = chengJi(1.3f, 0.13f);
//        System.out.println(f7);
//
//        float f8 = optimizeFloat(f7);
//        System.out.println(f8);
//
//        float f9 = chengJi(1.4f, 0.1400f);
//        System.out.println(f9);
//
//        float f10 = optimizeFloat(f9);
//        System.out.println(f10);
//
//        float f11 = addValuePercent(1.234567890f);
//        System.out.println(f11);
//
//        float ruleValue = 0.012f;
//        System.out.println(ruleValue);
//        System.out.println(Math.round(ruleValue));

        double value1 = 1.5d;
        double value2 = 0.0001d;

//        BigDecimal valueBigDecimal = BigDecimal.valueOf(value1.doubleValue());

        double d = 1.5d * 0.0001d;

//        BigDecimal valueBigDecimal11 = BigDecimal.valueOf(value1);
//        System.out.println(valueBigDecimal11);
//        BigDecimal valueBigDecimal22 = BigDecimal.valueOf(value2);
//        System.out.println(valueBigDecimal22);
//        BigDecimal valueBigDecimal33 = valueBigDecimal11.multiply(valueBigDecimal22);
//        System.out.println(valueBigDecimal33);


        Float number1 = 1.5f;
        Float number2 = 0.0001f;

        String str1 = String.valueOf(number1);
//        BigDecimal valueBigDecimalA = BigDecimal.valueOf(Double.parseDouble(str1));
//        System.out.println(valueBigDecimalA);
        String str2 = String.valueOf(number2);
        BigDecimal valueBigDecimalB = BigDecimal.valueOf(Double.parseDouble(str2));
//        System.out.println(valueBigDecimalB);
//        BigDecimal result = valueBigDecimalA.multiply(valueBigDecimalB);
//        System.out.println(result);
//        float resultOptimize = optimizeFloat(result);
//        System.out.println(resultOptimize);


        BigDecimal valueBigDecimal1 = BigDecimal.valueOf(number1.doubleValue());
//        System.out.println(valueBigDecimal1);
        BigDecimal valueBigDecimal2 = BigDecimal.valueOf(number2.doubleValue());
//        System.out.println(valueBigDecimal2);
        BigDecimal valueBigDecimal3 = valueBigDecimal1.multiply(valueBigDecimal2);
//        System.out.println(valueBigDecimal3);
        float number3 = optimizeFloat(valueBigDecimal3.floatValue());
//        System.out.println(number3);
        Float percent2 = addValuePercent(number3);
//        System.out.println(percent2);


//        System.out.println(d);
        double double1 = 1.5;
        double double2 = 0.0001;
        double double3 = double1 * double2;
        System.out.println("1.5d * 0.0001d: 第一次" + double3);
        double f = 1.5d * 0.0001d;
        System.out.println("1.5d * 0.0001d: 第二次" + f);
        BigDecimal bigDecimal = BigDecimal.valueOf(double1 * double2);
        System.out.println("1.5d * 0.0001d: 第3次" + bigDecimal);
        BigDecimal bigDecimal15 = new BigDecimal(1.5d);
        BigDecimal bigDecimal00001 = new BigDecimal(0.0001d);
        BigDecimal bigDecimall = bigDecimal15.multiply(bigDecimal00001);
        System.out.println("1.5d * 0.0001d: 第4次" + bigDecimall);
//        float f2 = optimizeFloat(f);
//        System.out.println(f2);
//        Float percent = addValuePercent(f2);
//        System.out.println(percent);

//        f2 = f2 * 100;
//        System.out.println(f2);
    }


    public static void checkInteger(Integer index) {
        index = index / 2;
    }

    public static Float chengJi(Float f1, Float f2) {
        return f1 * f2;
    }

    public static Float optimizeFloat(Float value) {
        if (value == null) {
            return null;
        }
        BigDecimal valueBigDecimal = BigDecimal.valueOf(value.doubleValue());
        valueBigDecimal = valueBigDecimal.setScale(6, RoundingMode.HALF_UP);
        return valueBigDecimal.floatValue();
    }

    public static Float optimizeFloat(BigDecimal valueBigDecimal) {
        if (valueBigDecimal == null) {
            return null;
        }
        valueBigDecimal = valueBigDecimal.multiply(BIG_DECIMAL_HUNDRED);
        valueBigDecimal = valueBigDecimal.setScale(7, RoundingMode.HALF_UP);
        return valueBigDecimal.floatValue();
    }

    /**
     * 处理浮点数精度问题
     *
     * @param value 规则 value 值
     * @return 规则 value 值
     */
    public static Float optimizeFloat2(Float value) {
        if (value == null) {
            return null;
        }
        BigDecimal valueBigDecimal = BigDecimal.valueOf(value.doubleValue());
        valueBigDecimal = valueBigDecimal.setScale(7, RoundingMode.HALF_UP);
        return valueBigDecimal.floatValue();
    }

    public static Float removeValuePercent(Float value) {
        if (value == null) {
            return null;
        }
        return BigDecimal.valueOf(value.doubleValue())
                .divide(BIG_DECIMAL_HUNDRED, 7, RoundingMode.HALF_UP)
                .floatValue();
    }

    private static final BigDecimal BIG_DECIMAL_HUNDRED = BigDecimal.valueOf(100);

    /**
     * 带有"率" % 的数值类型转换
     *
     * @param value 规则 value 值
     * @return 乘以 100 后的数据
     */
    public static Float addValuePercent(Float value) {
        if (value == null) {
            return null;
        }
        return BigDecimal.valueOf(value.doubleValue())
                .multiply(BIG_DECIMAL_HUNDRED)
                .floatValue();
    }


}
