package com.autumn.clever.最新马士兵亲授Java面试突击训练全套教程2021.demo;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/8/1 5:21 下午
 */
public class JolTest {

    public static void main(String[] args) {
//        Object o = new Object();
//        ClassLayout layout = ClassLayout.parseInstance(o);
//        String printable = layout.toPrintable();
//        System.out.println(printable);
//
//        synchronized (o) {
//            ClassLayout layout1 = ClassLayout.parseInstance(o);
//            String printable1 = layout.toPrintable();
//            System.out.println(printable1);
//        }
        // JUC -> java.util.concurrent

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            System.out.println(1);
        } catch (Exception e) {
            System.out.println(2);
        }

    }
}
