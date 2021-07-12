package com.autumn.clever.java8;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/5 6:16 下午
 */
public enum BizExceptionEnum {
    REPEAT_JOIN(1, "重复报名");

    private int code;
    private String desc;

    BizExceptionEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
