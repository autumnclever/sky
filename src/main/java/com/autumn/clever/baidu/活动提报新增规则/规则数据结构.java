package com.autumn.clever.baidu.活动提报新增规则;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2024-03-07 20:09
 **/
public class 规则数据结构 {

    private static final String RULE_TYPE_LIST_SQL_ORDER_BY = "rule_type asc limit %d, %d";

    public static void main(String[] args) {
        String sql = String.format(RULE_TYPE_LIST_SQL_ORDER_BY, 0, 1);
        System.out.println(sql);


//        List<String> lists = Lists.newArrayList(">", "<", "=", ">=", "<=", "!=");
//        for (String s : lists) {
//            switch (s){
//                case ">":
//                    System.out.println("大于");
//                    break;
//                    case "<":
//                        switch ()
//            }
//        }
//        boolean isTrue = true;
//        boolean isFalse = false;
//        boolean result1 = isTrue && isFalse || isTrue && isFalse;
//        boolean result2 = isFalse || isTrue && isFalse;

//        ActivityRuleCheckWayInfo checkWay1 = new ActivityRuleCheckWayInfo(1, ">");
//        ActivityRuleCheckWayInfo checkWay2 = new ActivityRuleCheckWayInfo(2, "<");
//        ActivityRuleCheckWayInfo checkWay3 = new ActivityRuleCheckWayInfo(3, "=");
//        ActivityRuleCheckWayInfo checkWay4 = new ActivityRuleCheckWayInfo(4, ">=");
//        ActivityRuleCheckWayInfo checkWay5 = new ActivityRuleCheckWayInfo(5, "<=");
//        ActivityRuleCheckWayInfo checkWay6 = new ActivityRuleCheckWayInfo(6, "!=");
//
//        List<ActivityRuleCheckWayInfo> checkWayList = Lists.newArrayList(
//                checkWay1, checkWay2, checkWay3, checkWay4, checkWay5, checkWay6);
//
//        String checkWayInfo = JsonUtils.toJson(checkWayList);
//        System.out.println(checkWayInfo);


    }
}
