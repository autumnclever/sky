package com.autumn.clever.需求.活动提报;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/14 7:10 下午
 */
public class CsvUtils {

    /**
     * 生成 CVS 文件
     *
     * @param outPutPath 文件路径
     * @param fileName   文件名称
     * @param tableHead  csv文件的列表头
     * @param exportData 源数据List
     * @return
     */
    public static File createCSVFile(String outPutPath, String fileName,
                                     List<List<Object>> tableHead, List<List<Object>> exportData) {
        try {
            File csvFile = new File(outPutPath + fileName);
            if (!csvFile.exists()) {
                csvFile.createNewFile();
            }
            CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"));
            if (tableHead != null && tableHead.size() > 0) {
                for (List<Object> list : tableHead) {
                    String[] head = conventList2Array(list);
                    if (head != null) {
                        csvWriter.writeNext(head);
                    }
                }
            }
            if (exportData != null && exportData.size() > 0) {
                for (List<Object> list : exportData) {
                    String[] data = conventList2Array(list);
                    if (data != null) {
                        csvWriter.writeNext(data);
                    }
                }
            }
            csvWriter.flush();
            csvWriter.close();
            return csvFile;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static File createCSVFile2(String outPutPath, String fileName,
                                      String[] tableHead, List<List<Object>> exportData) {
        try {
            File csvFile = new File(outPutPath + fileName);
            if (!csvFile.exists()) {
                csvFile.createNewFile();
            }
            CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"));
            csvWriter.writeNext(tableHead);
            if (exportData != null && exportData.size() > 0) {
                for (List<Object> list : exportData) {
                    String[] data = conventList2Array(list);
                    if (data != null) {
                        csvWriter.writeNext(data);
                    }
                }
            }
            csvWriter.flush();
            csvWriter.close();
            return csvFile;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static String[] convertList2Array(List<Object> list, Class clazz) {
        Field[] filelds = clazz.getDeclaredFields();
        String[] strs = new String[filelds.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = filelds[i].toString();
        }
        return strs;
    }

    private static String[] conventList2Array(List<Object> list) {
        if (list != null && !list.isEmpty()) {
            String[] result = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                Object data = list.get(i);
                if (data == null) {
                    result[i] = "";
                } else {
                    result[i] = String.valueOf(data);
                }
            }
            return result;
        }
        return null;
    }

    public static File createCSVFile3(String outPutPath, String fileName, String[] tableHead, List<Object> exportData, Class clazz) {
        try {
            File csvFile = new File(outPutPath + fileName);
            if (!csvFile.exists()) {
                csvFile.createNewFile();
            }
            CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"));
            csvWriter.writeNext(tableHead);
            if (exportData != null && exportData.size() > 0) {
                for (Object data : exportData) {
                    String[] dataStr = getClassValueArr(data, clazz);
                    if (dataStr != null) {
                        csvWriter.writeNext(dataStr);
                    }
                }
            }
            csvWriter.flush();
            csvWriter.close();
            return csvFile;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static String[] getClassValueArr(Object object, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String[] valueStr = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            String value = getFieldValue(fields[i], object, clazz);
            valueStr[i] = value;
        }
        return valueStr;
    }

    public static String getFieldValue(Field field, Object object, Class clazz) {
        try {
            String fieldName = field.getName();
            String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method method = clazz.getMethod("get" + methodName);
            Object value = method.invoke(object);
            return String.valueOf(value);
        } catch (Exception e) {

        }
        return "";
    }
}
