/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu.营销的一些实体类;

import lombok.Data;

import java.util.Date;

/**
 * FileName: CouponInfo
 * Author:   zhangqiuying
 * Date:     2024/7/1 18:26
 * Description:
 */
@Data
public class CouponInfo {

    private Long id;

    private String batchId;

    private String name;

    private Long ucId;

    private Long shopId;

    private Long channel;

    private Integer couponType;

    private Date takeStart;

    private Date takeEnd;

    private Date useStart;

    private Date useEnd;

    private Long limitNum;

    private Long putNum;

    private Integer rangeType;

    private String rangeProduct;

    private Long fullPrice;

    private Long salePrice;

    private Long mostPrice;

    private Integer status;

    private String remark;

    private Integer isDelete;

    private Long creater;

    private Date createTime;

    private Long updater;

    private Date updateTime;

    private String rangeUcid;

    private Long appid;

    private Integer bizType;

    private Long budgetUnId;

    private Integer estimateUsage;

    private Integer priceType;

    private String extraInfo;

    private Integer limitNumType;

    private Integer useTimeType;

    private Integer timeUnit;

    private Integer timeValue;

    private Integer auditStatus;

    private Integer auditVersion;

    private String extraDiscountRule;

    private String outId;

    private Long subAppId;

    private Long promotionId;

    private Integer takeStatus;

    private String auditReason;

    private String useLimitRule;

    private Integer activityFlag;

    private Long dayLimitNum;

    private Integer limitChannel;

    private Integer couponVersion;

    private Integer testFlag;

    private Integer couponCodeType;

    private String operator;

    private Integer sendNum;

    private String cashierPromotionId;

    private Integer stockSplitFlag;

    private Integer tag;

    private Integer useLimitChannel;

    private String extraBizInfo;

    private String takeLimitInfo;

    private Integer limitApp;

    private String limitUserTag;

    private String limitProduct;

    private String limitSid;

    private String limitSubChannelInfo;

    private String limitContentBindType;

    private String limitUseInfo;

}
