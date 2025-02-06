/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu.营销的一些实体类;

import lombok.Getter;

/**
 *
 * FileName: UseTimeEnum
 * Author:   zhangqiuying
 * Date:     2024/7/1 18:28
 * Description:
 */
@Getter
public enum UseTimeEnum {

    SET_BEGIN_END(1, "业务方设置使用开始和结束时间"),
    /**
     * 相对时间：当规定领取 5 日后失效，10/1 日 23:00 领取后，10/6 23:00 失效；
     */
    TAKE_BEGIN(2, "领取之后生效，多久可使用"),
    TAKE_DEFINE(3, "领取之后，定义优惠劵的可使用时间"),
    /**
     * 自然相对时间：当规定领取 5 个自然日后失效，10/1 日 23:00 领取后，10/6 00:00 失效；
     */
    TAKE_BEGIN_NATURAL_TIME(4, "领取之后生效，自然时间失效");

    /**
     * code
     */
    private Integer code;
    /**
     * 描述
     */
    private String desc;

    UseTimeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UseTimeEnum getUseTimeEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (UseTimeEnum useTimeEnum : UseTimeEnum.values()) {
            if (useTimeEnum.getCode().equals(code)) {
                return useTimeEnum;
            }
        }
        return null;
    }
}
