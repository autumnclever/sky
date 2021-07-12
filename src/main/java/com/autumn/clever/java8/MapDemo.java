package com.autumn.clever.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.compress.utils.Lists;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/9 2:18 下午
 */
public class MapDemo {
    public static void main(String[] args) {
        List<ActivityScope> activityScopeList = Lists.newArrayList();
        for (int i = 1; i < 10; i++) {
            String auditReason = "";
            if (i % 2 == 0) {
                auditReason = "auditReason";
            }
            ActivityScope activityScope = new ActivityScope(String.valueOf(i), i, auditReason);
            activityScopeList.add(activityScope);
        }

//        Map<String, ActivityScope> activityScopeMap = activityScopeList.stream()
//                .collect(Collectors.toMap(ActivityScope::getPrimaryId, Function.identity(), (k1, k2) -> k1));
//
//        for (Map.Entry<String, ActivityScope> entry : activityScopeMap.entrySet()) {
//            System.out.println(entry);
//        }
//        Map<Long, ActivityScope> activityScopeMap2 = activityScopeList.stream()
//                .collect(Collectors.toMap(activityScope -> Long.valueOf(activityScope.getPrimaryId()), Function.identity(), (k1, k2) -> k1));
//        for (Map.Entry<Long, ActivityScope> entry : activityScopeMap2.entrySet()) {
//            System.out.println(entry);
//        }

        Map<Boolean, Map<Long, List<ActivityScope>>> map = activityScopeList.stream()
                .collect(Collectors.groupingBy(activityScope -> Strings.isBlank(activityScope.getAuditReason()),
                        Collectors.groupingBy(activityScope -> Long.parseLong(activityScope.getPrimaryId()))));
        Set<Map.Entry<Boolean, Map<Long, List<ActivityScope>>>> entrySet = map.entrySet();
        for (Map.Entry<Boolean, Map<Long, List<ActivityScope>>> entry : entrySet) {
            System.out.println(entry);
        }
    }
}

@Data
@AllArgsConstructor
@ToString
class ActivityScope {
    private String primaryId;

//    private Boolean needUpdate;

    private Integer auditStatus;

    private String auditReason;
}
