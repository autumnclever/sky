package com.autumn.clever.需求.活动提报;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/12 5:24 下午
 */
public class TestDemo {
    public static void main(String[] args) {
//        String fileUrl = "bos://bucket/objectKey";
//        String[] strings = fileUrl.split("/");
//        for (int i = 0; i < strings.length; i++) {
//            System.out.println(strings[i]);
//        }

        ActivitySignUpFileRecord record1 = new ActivitySignUpFileRecord(1L, 15417L, 26771928L, 1, new Date(1626159300000L));
        ActivitySignUpFileRecord record2 = new ActivitySignUpFileRecord(2L, 7181L, 26771928L, 1, new Date(1626159360000L));
        ActivitySignUpFileRecord record3 = new ActivitySignUpFileRecord(3L, 15417L, 26771928L, 1, new Date(1626159420000L));
        ActivitySignUpFileRecord record4 = new ActivitySignUpFileRecord(4L, 15417L, 26771928L, 1, new Date(1626159480000L));
        ActivitySignUpFileRecord record5 = new ActivitySignUpFileRecord(5L, 7181L, 26782728L, 1, new Date(1626159540000L));
        ActivitySignUpFileRecord record6 = new ActivitySignUpFileRecord(6L, 7181L, 26782728L, 1, new Date(1626159240000L));
        ActivitySignUpFileRecord record7 = new ActivitySignUpFileRecord(7L, 15417L, 26782728L, 1, new Date(1626159180000L));
        ActivitySignUpFileRecord record8 = new ActivitySignUpFileRecord(8L, 15417L, 26782728L, 1, new Date(1626159120000L));

        List<ActivitySignUpFileRecord> recordList = Lists.newArrayList();
        recordList.add(record1);
        recordList.add(record2);
        recordList.add(record3);
        recordList.add(record4);
        recordList.add(record5);
        recordList.add(record6);
        recordList.add(record7);
        recordList.add(record8);
        recordList.sort(Comparator.comparing(ActivitySignUpFileRecord::getCreateTime));
        System.out.println("---------");
        System.out.println("原始数据");
        for (ActivitySignUpFileRecord record : recordList) {
            System.out.println(record.toString());
        }

        System.out.println("---------");
        Map<Long, Map<Integer, List<ActivitySignUpFileRecord>>> undoneFileRecordMap = recordList.stream()
                .collect(
                        Collectors.groupingBy(ActivitySignUpFileRecord::getActivityId,
                                Collectors.groupingBy(ActivitySignUpFileRecord::getStatus)));

        List<Long> activityIdList = new ArrayList<>(undoneFileRecordMap.keySet());
        for (Long activityId : activityIdList) {
            System.out.println("activityId:" + activityId);
            Map<Integer, List<ActivitySignUpFileRecord>> activityMap = undoneFileRecordMap.get(activityId);
            List<Integer> statusList = new ArrayList<>(activityMap.keySet());
            for (Integer status : statusList) {
                System.out.println("status:" + status);
                List<ActivitySignUpFileRecord> fileRecords = activityMap.get(status);
                fileRecords.forEach(fileRecord -> System.out.println(fileRecord));
            }
        }

        System.out.println("---------");
        System.out.println("初始化 todoRecordList");
        List<ActivitySignUpFileRecord> todoRecordList = Lists.newArrayList();
        for (Long activityId : activityIdList) {
            System.out.println("activityId:" + activityId);
            Map<Integer, List<ActivitySignUpFileRecord>> activityMap = undoneFileRecordMap.get(activityId);
            if (activityMap.get(2) == null && activityMap.get(1) != null) {
                todoRecordList.add(activityMap.get(1).get(0));
            }
//            if (activityMap.get(2) == null && activityMap.get(1) != null) {
//                todoRecordList.addAll(activityMap.get(1));
//            }
        }
        todoRecordList.forEach(todoRecord -> System.out.println(todoRecord.toString()));
        System.out.println("----------");
        System.out.println("todoRecordList 合并完成，按照创建时间排序:");
        List<ActivitySignUpFileRecord> copyTodoRecordList = new ArrayList<>(todoRecordList);
        List<ActivitySignUpFileRecord> copyTodoRecordList1 = new ArrayList<>(todoRecordList);
        todoRecordList.sort(Comparator.comparing(ActivitySignUpFileRecord::getCreateTime));
        todoRecordList.forEach(todoRecord -> System.out.println(todoRecord.toString()));


        System.out.println("去重方法1：");
        System.out.println("----------");
        System.out.println("todoRecordList 按照活动id去重:");
        todoRecordList = todoRecordList.stream()
                .filter(distinctByKey(todoRecord -> todoRecord.getActivityId()))
                .collect(Collectors.toList());
        todoRecordList.forEach(todoRecord -> System.out.println(todoRecord.toString()));

        System.out.println("----------");
        System.out.println("todoRecordList 按照shopid去重");
        todoRecordList = todoRecordList.stream()
                .filter(distinctByKey(todoRecord -> todoRecord.getShopId()))
                .collect(Collectors.toList());
        todoRecordList.forEach(todoRecord -> System.out.println(todoRecord.toString()));

        System.out.println("去重方法2：");
        copyTodoRecordList = copyTodoRecordList.stream()
                .sorted(Comparator.comparing(ActivitySignUpFileRecord::getCreateTime))
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(
                                Comparator.comparing(record -> record.getActivityId() + ":" + record.getShopId()))),
                        ArrayList::new));

        copyTodoRecordList.forEach(copyTodoRecord -> System.out.println(copyTodoRecord.toString()));


        System.out.println("去重方法3：");
        copyTodoRecordList1 = copyTodoRecordList1.stream()
                .sorted(Comparator.comparing(ActivitySignUpFileRecord::getCreateTime))
                .filter(distinctByKey(ActivitySignUpFileRecord::getShopId))
                .collect(Collectors.toList());

        copyTodoRecordList1.forEach(copyTodoRecord1 -> System.out.println(copyTodoRecord1.toString()));
//        List<ActivitySignUpFileRecord> distinctClass = copyTodoRecordList.stream()
//                .collect(
//                        Collectors.collectingAndThen(
//                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getActivityId() + ";" + o.getShopId()))), ArrayList::new));


    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
class ActivitySignUpFileRecord {
    private Long id;

//    private Long appId;

    private Long shopId;

//    private Long ucId;

    private Long activityId;

//    private String fileName;

    private Integer status;

//    private Integer totalCount;
//
//    private Integer successCount;
//
//    private Integer failCount;

    private Date createTime;

//    private Date updateTime;
}
