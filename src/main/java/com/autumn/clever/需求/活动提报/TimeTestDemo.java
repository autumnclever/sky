package com.autumn.clever.需求.活动提报;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/14 12:02 下午
 */
public class TimeTestDemo {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        sb.append(year).append("_").append(month).append("_").append(day);
        String date = sb.toString();
        System.out.println(date);
        String str = RandomStringUtils.random(10, true, false);
        System.out.println(str);


        ActivitySignUpProductDTO signUpProductDTO1 = new ActivitySignUpProductDTO(1L, 2L, 3L, 1, "signUpProductDTO1");
        ActivitySignUpProductDTO signUpProductDTO2 = new ActivitySignUpProductDTO(1L, 2L, 3L, 2, "signUpProductDTO2");
        ActivitySignUpProductDTO signUpProductDTO3 = new ActivitySignUpProductDTO(1L, 2L, 3L, 3, "signUpProductDTO3");
        ActivitySignUpProductDTO signUpProductDTO4 = new ActivitySignUpProductDTO(1L, 2L, 3L, 4, "signUpProductDTO4");
        ActivitySignUpProductDTO signUpProductDTO5 = new ActivitySignUpProductDTO(1L, 2L, 3L, 5, "");
        ActivitySignUpProductDTO signUpProductDTO6 = new ActivitySignUpProductDTO(1L, 2L, 3L, 6, "");
        List<ActivitySignUpProductDTO> signUpProductDTOList = new ArrayList<>();
        signUpProductDTOList.add(signUpProductDTO1);
        signUpProductDTOList.add(signUpProductDTO2);
        signUpProductDTOList.add(signUpProductDTO3);
        signUpProductDTOList.add(signUpProductDTO4);
        signUpProductDTOList.add(signUpProductDTO5);
        signUpProductDTOList.add(signUpProductDTO6);

        IntSummaryStatistics totalStock = signUpProductDTOList.stream()
                .collect(Collectors.summarizingInt(ActivitySignUpProductDTO::getSaleStock));
//        System.out.println(totalStock.getSum());
//        Integer total = signUpProductDTOList.stream().reduce(0, ActivitySignUpProductDTO::getSaleStock);

        int total2 = signUpProductDTOList.stream().mapToInt(ActivitySignUpProductDTO::getSaleStock).sum();
//        System.out.println(total2);

        List<ActivitySignUpProductDTO> canSignUpProductDTOList = signUpProductDTOList.stream()
                .filter(signUpProductDTO -> Strings.isBlank(signUpProductDTO.getFailReason()))
                .collect(Collectors.toList());
        System.out.println("canSignUpProductDTOList:");
        canSignUpProductDTOList.forEach(signUpProductDTO -> System.out.println(signUpProductDTO.toString()));
        System.out.println("signUpProductDTOList:");
        signUpProductDTOList.forEach(signUpProductDTO -> System.out.println(signUpProductDTO.toString()));
        System.out.println("canNotSignUpProductDTOList:");
        signUpProductDTOList.removeAll(canSignUpProductDTOList);
        signUpProductDTOList.forEach(signUpProductDTO -> System.out.println(signUpProductDTO.toString()));

        AtomicInteger signUpSuccess = new AtomicInteger(0);
        System.out.println(signUpSuccess);
    }
}

