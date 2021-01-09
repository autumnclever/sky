package com.autumn.clever.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/5 上午10:58
 */
@Data
public class SaleMovieDO {
    private Integer movieId;

    private String movieName;

    /**
     * 排名位置
     */
    private Integer sort;

    /**
     * 入口uv
     */
    private Integer entranceUV;

    /**
     * 订单提交uv
     */
    private Integer orderUV;

    /**
     * 支付uv
     */
    private Integer payUV;

    /**
     * 平均排名
     */
    private Double averageSort;

    /**
     * 订单转化率
     */
    private Double orderConversion;

    /**
     * 支付转化率
     */
    private Double payConversion;

    /**
     * 日期
     */
    private String date;

    public SaleMovieDO() {
    }

    public SaleMovieDO(Integer sort, Integer entranceUV, Integer orderUV, Integer payUV, Double orderConversion, Double payConversion, String date) {
        this.sort = sort;
        this.entranceUV = entranceUV;
        this.orderUV = orderUV;
        this.payUV = payUV;
        this.orderConversion = orderConversion;
        this.payConversion = payConversion;
        this.date = date;
    }
}
