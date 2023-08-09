package com.autumn.clever.file3;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-07-31 19:36
 **/
public class CouponInfo {
    private String platformPromotionId;

    public CouponInfo() {
    }

    public CouponInfo(String platformPromotionId) {
        this.platformPromotionId = platformPromotionId;
    }

    public String getPlatformPromotionId() {
        return platformPromotionId;
    }

    public void setPlatformPromotionId(String platformPromotionId) {
        this.platformPromotionId = platformPromotionId;
    }
}
