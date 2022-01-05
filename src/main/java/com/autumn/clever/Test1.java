package com.autumn.clever;


import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/4 上午10:15
 */
public class Test1 {
    public static void main(String[] args) {
//        Date date = getOneDayAgoHourDate(12);
//        System.out.println(date.toString());
//        Integer i = 2;

//        System.out.println(i.equals(null));
//        Date endTime = new Date(System.currentTimeMillis());
//        System.out.println(endTime);
////        endTime = getDayAfterTime(endTime, 5);
//        System.out.println(endTime);
//        String query = "query1;query2;query3";
//        System.out.println(query);
//        String queryArray = query.replace(";", "\r\n");
//        System.out.println(queryArray);

//        Date[] date1 = parseParamTime("");
//        System.out.println(date1[0]);
//        System.out.println(date1[1]);
//        Date[] date2 = parseParamTime("a,b");
//        System.out.println(date2[0]);
//        System.out.println(date2[1]);
//
//        ContentDTO contentDTO = new ContentDTO();
//        contentDTO.setType(1);
//        contentDTO.setAuthorId(0L);
//        contentDTO.setCoverImage("sohdfoa");
//        contentDTO.setQuery("sihdfia");
//        contentDTO.setDetail("isdhfap");
//        contentDTO.setCategoryId(1);
//        contentDTO.setSubcategoryId(2);
//        contentDTO.setVideo("iashdfp");
//        ContentAddRequest request = new ContentAddRequest();
//        BeanUtils.copyProperties(contentDTO, request);
//        ContentAddRequest request1 =request;


//        String qiuying1 = "qiuying";
//        String qiuying2 = "秋颖";
//        String qiuying3 = "qiu ying";
//        System.out.println(qiuying1.length());
//        System.out.println(qiuying2.length());
//        System.out.println(qiuying3.length());


//        int step = 0;
//        int step1 = 1;
//        int step2 = 2;
//        int step3 = 4;
//        int step4 = 8;
//        int step5 = 16;
//        step = step | step1;
//        System.out.println(step);
//        step = step | step2;
//        System.out.println(step);
//        step = step | step3;
//        System.out.println(step);
//        step = step | step4;
//        System.out.println(step);
//        step = step | step5;
//        System.out.println(step);

        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        int m = (list.size() + 2 - 1) / 2;
        List<List<Integer>> splitList = Stream.iterate(0, n -> n + 1).limit(m).parallel().map(
                a -> list.stream().skip(a * 2).limit(2).parallel().collect(Collectors.toList())
        ).collect(Collectors.toList());
        for (List<Integer> list1 : splitList) {
            System.out.println(list1);
        }
        Lists.partition(list, 2);
        for (List<Integer> list1 : splitList) {
            System.out.println(list1);
        }
    }

    public static Date getOneDayAgoHourDate(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(HOUR_OF_DAY, -hour);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);
        return calendar.getTime();
    }

//    public static Date getDayAfterTime(Date endTime, Integer timeValue) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(endTime);
//        calendar.add(Calendar.DATE, timeValue);
//        calendar.set(Calendar.HOUR_OF_DAY, NumberUtils.INTEGER_ZERO);
//        calendar.set(Calendar.MINUTE, NumberUtils.INTEGER_ZERO);
//        calendar.set(Calendar.SECOND, NumberUtils.INTEGER_ZERO);
//        calendar.set(Calendar.MILLISECOND, NumberUtils.INTEGER_ZERO);
//        endTime = calendar.getTime();
//
//        return endTime;
//    }

    public static Date[] parseParamTime(String time) {
        Date[] dateArr = new Date[2];
        if (StringUtils.isBlank(time)) {
            return dateArr;
        }
        String[] timeArr = time.split(",");
        if (timeArr.length != 2) {
            return dateArr;
        }
        String beginTimeStr = timeArr[NumberUtils.INTEGER_ZERO];
        String endTimeStr = timeArr[NumberUtils.INTEGER_ONE];
        beginTimeStr = StringUtils.isNumeric(beginTimeStr) ? beginTimeStr : "0";
        endTimeStr = StringUtils.isNumeric(endTimeStr) ? endTimeStr : "0";
        Date timeBegin = new Date(Long.parseLong(beginTimeStr) * 1000);
        Date timeEnd = new Date(Long.parseLong(endTimeStr) * 1000);
        dateArr[NumberUtils.INTEGER_ZERO] = timeBegin;
        dateArr[NumberUtils.INTEGER_ONE] = timeEnd;
        return dateArr;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ContentDTO {

    /**
     * 内容类型
     */
    private Integer type;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 一级分类id
     */
    private Integer categoryId;

    /**
     * 二级分类名称
     */
    private Integer subcategoryId;

    /**
     * 标题
     */
    private String title;


    /**
     * 封面
     */
    private String coverImage;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 正文
     */
    private String detail;

    /**
     * 视频
     */
    private String video;


    /**
     * 泛化query，以";"分割
     */
    private String query;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ContentAddRequest {

    /**
     * 内容类型
     */
    private Integer type;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 一级分类id
     */
    private Integer categoryId;

    /**
     * 二级分类id
     */
    private Integer subcategoryId;

    /**
     * 标题
     */
    private String title;


    /**
     * 封面
     */
    private String coverImage;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 正文
     */
    private String detail;

    /**
     * 视频
     */
    private String video;

    /**
     * 泛化query，以";"分割
     */
    private String query;
}