package com.autumn.clever.mmdb;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/4 上午10:39
 */
public enum RelativePositionChangeTypeEnum {
    /**
     * 相等
     */
    EQUALS(0, "相等"),
    /**
     * 上升
     */
    RISE(1, "上升"),
    /**
     * 下降
     */
    DROP(2, "下降");


    private final int value;
    private final String desc;

    RelativePositionChangeTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private final static Map<Integer, RelativePositionChangeTypeEnum> VAR_MAP = new HashMap<>();

    static {
        for (RelativePositionChangeTypeEnum e : values()) {
            VAR_MAP.put(e.getValue(), e);
        }
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static RelativePositionChangeTypeEnum getEnumByValue(int value) {
        return VAR_MAP.get(value);
    }
}
