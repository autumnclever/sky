/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu.营销的一些实体类;

import lombok.Getter;

/**
 * FileName: TimeUnitEnum
 * Author:   zhangqiuying
 * Date:     2024/7/1 18:32
 * Description:
 */
@Getter
public enum TimeUnitEnum {

    HOUR(1, "小时"),

    DAY(2, "天"),

    MONTH(3, "月");

    /**
     * code
     */
    private Integer code;
    /**
     * 描述
     */
    private String desc;

    TimeUnitEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TimeUnitEnum getTimeUnitEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (TimeUnitEnum timeUnitEnum : TimeUnitEnum.values()) {
            if (timeUnitEnum.getCode().equals(code)) {
                return timeUnitEnum;
            }
        }
        return null;
    }

}
