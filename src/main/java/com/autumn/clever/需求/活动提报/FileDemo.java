package com.autumn.clever.需求.活动提报;

import org.apache.commons.compress.utils.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang.reflect.MethodUtils.invokeMethod;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/14 7:13 下午
 */
@RequestMapping("/fileDemo")
@RestController
public class FileDemo {

    public static void main(String[] args) throws NoSuchFieldException {
        ActivitySignUpProductDTO signUpProductDTO1 = new ActivitySignUpProductDTO(1L, 2L, 3L, 1, "signUpProductDTO1");
        ActivitySignUpProductDTO signUpProductDTO2 = new ActivitySignUpProductDTO(1L, 2L, 3L, 1, "signUpProductDTO1");
//        Class clazz = signUpProductDTO1.getClass();
////        Class clazz = ActivitySignUpProductDTO.class;
//        Field[] filelds = clazz.getDeclaredFields();
//        String[] strs = new String[filelds.length];
//        for (int i = 0; i < strs.length; i++) {
//            strs[i] = filelds[i].toString();
//            Field field = signUpProductDTO1.getClass().getDeclaredField(filelds[i].getName());
//            System.out.println(field.toString());
//        }
//
        List<ActivitySignUpProductDTO> list = Arrays.asList(signUpProductDTO1, signUpProductDTO2);
//        List<String[]> strList = convertList2Array(list, ActivitySignUpProductDTO.class);
//        for (String[] strArray : strList) {
//            for (String str : strArray) {
//                System.out.println(str);
//            }
//        }


//        String[] values = getClassValueArr(signUpProductDTO1, ActivitySignUpProductDTO.class);
//        for (String str : values) {
//            System.out.println(str);
//        }

        List<String[]> strArrList = Lists.newArrayList();
        list.stream().forEach(activitySignUpProductDTO -> {
            String[] values = getClassValueArr(activitySignUpProductDTO, ActivitySignUpProductDTO.class);
            strArrList.add(values);
        });

        for (String[] strArr : strArrList) {
            for (String str : strArr) {
                System.out.println(str);
            }
        }
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

    private static List<String[]> convertList2Array(List<Object> list, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String[]> strList = Lists.newArrayList();
        for (Object o : list) {
//            Field[] fields = o.getClass().getFields();
            String[] strs = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
//                String value = getValue(fields[i], o);
//                strs[i] = value;
            }
            strList.add(strs);
        }
        return strList;
    }

    private static String getValue(Object object, Field field) {
        return getFieldValue(object, field.getName());
    }

    public static String getFieldValue(Object owner, String fieldName) {
        try {
            if (invokeMethod(owner, fieldName) != null) {
                return invokeMethod(owner, fieldName).toString();
            } else {
                return "null";
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static Object invokeMethod(Object owner, String fieldName) {
        Class ownerClass = owner.getClass();
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = null;
        try {
            method = ownerClass.getMethod("get" + methodName);
        } catch (SecurityException e) {

        } catch (NoSuchMethodException e) {
            return "";
        }
        try {
            return method.invoke(owner);
        } catch (Exception e) {
            return "";
        }
    }

    @RequestMapping("/generateCSVFile")
    public Object generateCSVFile(HttpServletResponse response) {
        ActivitySignUpProductDTO signUpProductDTO1 = new ActivitySignUpProductDTO(1L, 2L, 3L, 1, "signUpProductDTO1");
        ActivitySignUpProductDTO signUpProductDTO2 = new ActivitySignUpProductDTO(1L, 2L, 3L, 2, "signUpProductDTO2");
        ActivitySignUpProductDTO signUpProductDTO3 = new ActivitySignUpProductDTO(1L, 2L, 3L, 3, "signUpProductDTO3");
        ActivitySignUpProductDTO signUpProductDTO4 = new ActivitySignUpProductDTO(1L, 2L, 3L, 4, "signUpProductDTO4");
        ActivitySignUpProductDTO signUpProductDTO5 = new ActivitySignUpProductDTO(1L, 2L, 3L, 5, "signUpProductDTO5");
        ActivitySignUpProductDTO signUpProductDTO6 = new ActivitySignUpProductDTO(1L, 2L, 3L, 6, "signUpProductDTO6");
        List<ActivitySignUpProductDTO> signUpProductDTOList = new ArrayList<>();
        signUpProductDTOList.add(signUpProductDTO1);
        signUpProductDTOList.add(signUpProductDTO2);
        signUpProductDTOList.add(signUpProductDTO3);
        signUpProductDTOList.add(signUpProductDTO4);
        signUpProductDTOList.add(signUpProductDTO5);
        signUpProductDTOList.add(signUpProductDTO6);

        String filePath = System.getProperty("java.io.tmpdir") + "/";
        String fileName = System.nanoTime() + ".csv";
//        List<List<Object>> exportData = Lists.newArrayList();
//        for (ActivitySignUpProductDTO productDTO : signUpProductDTOList) {
//            List<Object> item = Lists.newArrayList();
//            item.add(productDTO.getSpuId());
//            item.add(productDTO.getSkuId());
//            item.add(productDTO.getSalePrice());
//            item.add(productDTO.getSaleStock());
//            item.add(productDTO.getFailReason());
//            exportData.add(item);
//        }
        List<Object> exportData = signUpProductDTOList.stream().collect(Collectors.toList());
//        File csvFile = CsvUtils.createCSVFile(filePath, fileName, ACTIVITY_SIGN_UP_FAIL_TABLE_HEAD, exportData);
//        File csvFile = CsvUtils.createCSVFile2(filePath, fileName, ACTIVITY_SIGN_UP_FAIL_FILE_CH_TITLES, exportData);
        File csvFile = CsvUtils.createCSVFile3(filePath, fileName, ACTIVITY_SIGN_UP_FAIL_FILE_CH_TITLES, exportData, ActivitySignUpProductDTO.class);
        exportExcel(response, csvFile, fileName);
        return true;
    }

    /**
     * 活动提报报名失败 excel 文件表头
     */
    public static final List<List<Object>> ACTIVITY_SIGN_UP_FAIL_TABLE_HEAD = Arrays.asList(
            Arrays.asList("商品id（SPUID）"),
            Arrays.asList("规格id（SKUID）"),
            Arrays.asList("SKU活动价格（元）"),
            Arrays.asList("SKU活动库存"),
            Arrays.asList("导入失败原因")
    );

    public static final String[] ACTIVITY_SIGN_UP_FAIL_FILE_CH_TITLES =
            {"商品id（SPUID）", "规格id（SKUID）", "SKU活动价格（元）", "SKU活动库存", "导入失败原因"};

    public void exportExcel(HttpServletResponse response, File file, String fileName) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");

            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            InputStream in = new FileInputStream(file.getPath());
            OutputStream out = response.getOutputStream();
            int count = 0;
            byte[] by = new byte[1024];
            while ((count = in.read(by)) != -1) {
                out.write(by, 0, count);//将缓冲区的数据输出到浏览器
            }
            in.close();
            out.flush();
            out.close();
        } catch (Exception e) {

        }
    }
}
