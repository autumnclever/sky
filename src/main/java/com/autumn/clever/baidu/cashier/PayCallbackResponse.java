package com.autumn.clever.baidu.cashier;

import lombok.Data;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-06-16 15:05
 **/
@Data
public class PayCallbackResponse {

    private Integer errno;

    private String msg;

    private Object data;

    /**
     * 核销状态
     */
    public enum ConsumeStatus {
        NOCONSUMED(1, "未核销（不结算）"), CONSUMED(2, "已核销（需结算）"), UNKNOWN(0, "是否标记核销");

        private Integer value;
        private String desc;

        ConsumeStatus(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public Integer value() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
