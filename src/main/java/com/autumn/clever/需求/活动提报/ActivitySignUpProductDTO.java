package com.autumn.clever.需求.活动提报;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/14 2:28 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivitySignUpProductDTO {

    /**
     * 商品 spuId
     */
    private Long spuId;

    /**
     * 商品 skuId
     */
    private Long skuId;

    /**
     * 商品 sku 活动价格
     */
    private Long salePrice;

    /**
     * 商品 sku 活动库存
     */
    private Integer saleStock;

    /**
     * 导入失败原因
     */
    private String failReason;

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getSaleStock() {
        return saleStock;
    }

    public void setSaleStock(Integer saleStock) {
        this.saleStock = saleStock;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
