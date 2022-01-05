package com.autumn.clever.grape;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/11/15 4:39 下午
 */
@Getter
@AllArgsConstructor
public enum ContentStatusEnum {

    UNKNOWN(-1, "未知"),
    TO_BE_ONLINE(1, "待上线"),
    ONLINE(2, "上线"),
    OFFLINE(3, "下线"),
    ;

    /**
     * 根据上下线状态 status 获取状态枚举
     *
     * @param status 上下线状态值
     * @return 上下线状态枚举
     */
    public static ContentStatusEnum getContentStatusEnum(Integer status) {
        for (ContentStatusEnum contentStatusEnum : ContentStatusEnum.values()) {
            if (contentStatusEnum.status.equals(status)) {
                return contentStatusEnum;
            }
        }
        return UNKNOWN;
    }

    private Integer status;
    private String desc;
}
