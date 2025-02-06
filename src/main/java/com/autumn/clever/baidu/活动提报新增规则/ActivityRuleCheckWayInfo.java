package com.autumn.clever.baidu.活动提报新增规则;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2024-03-11 16:35
 **/
public class ActivityRuleCheckWayInfo {

    /**
     * 校验方式id
     */
    private Integer wayId;

    /**
     * 校验方式描述
     */
    private String wayDesc;

    public ActivityRuleCheckWayInfo() {
    }

    public ActivityRuleCheckWayInfo(Integer wayId, String wayDesc) {
        this.wayId = wayId;
        this.wayDesc = wayDesc;
    }

    public Integer getWayId() {
        return wayId;
    }

    public void setWayId(Integer wayId) {
        this.wayId = wayId;
    }

    public String getWayDesc() {
        return wayDesc;
    }

    public void setWayDesc(String wayDesc) {
        this.wayDesc = wayDesc;
    }
}
