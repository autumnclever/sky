package com.autumn.clever.baidu.finance.july2;

import com.autumn.clever.baidu.finance.CouponBizTypeEnum;
import com.autumn.clever.baidu.finance.CouponInfo;
import com.autumn.clever.baidu.finance.DiscountInfo;
import com.autumn.clever.common.JsonUtils;
import com.autumn.clever.goodcoder.FileUtils;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @program: sky
 * @description: 处理财务平台优惠券
 * @author: zhangqiuying
 * @create: 2023-08-03 17:31
 **/
public class DealPlatformCoupon2 {

    private static String FILE_TEST_ORDER = "/file/july2/payOrRefundSettleOrder.txt";

    private static String FILE_ORDER = "/file/july2/payOrRefundSettleOrder.txt";

    private static String FILE_PLATFORM_DISCOUNT_COUPON = "/file/july2/subOrderDiscountInfo.txt";

    private static String FILE_COUPON_INFO = "/file/july2/allCouponInfoList.txt";

    private static String FILE_PAY_TIME = "/file/july2/payAndRefundOtherInfo.txt";

    private static String FILE_RESULT = "/Users/zhangqiuying/autumn/sky/src/main/resources/file/july2/result2.txt";

    public static void main(String[] args) {
        List<String> orderList = FileUtils.readLinesFromClasspath(FILE_ORDER);
        List<String> platformDiscountInfo = FileUtils.readLinesFromClasspath(FILE_PLATFORM_DISCOUNT_COUPON);
        List<String> couponList = FileUtils.readLinesFromClasspath(FILE_COUPON_INFO);
        List<String> otherList = FileUtils.readLinesFromClasspath(FILE_PAY_TIME);

        Map<String, String> couponMap = Maps.newHashMap();
        couponList.forEach(line -> {
            String[] arrays = line.split("\t");
            couponMap.put(arrays[0], arrays[1]);
        });


        Map<Long, CouponInfo> platDiscountMap = platformDiscountInfo.parallelStream()
                .map(line -> {
                    PlatDiscount platDiscount = new PlatDiscount();
                    String[] arrays = line.split("\t");
                    Long subOrderId = Long.valueOf(arrays[0]);
                    DiscountInfo discountInfo = JsonUtils.jsonToObject(arrays[1], DiscountInfo.class);
                    List<CouponInfo> couponInfoList = discountInfo.getCouponInfoList();
                    CouponInfo couponInfo = new CouponInfo();
                    if (CollectionUtils.isNotEmpty(couponInfoList)) {
                        for (CouponInfo c : couponInfoList) {
                            if (c.getCouponBizType().equals(CouponBizTypeEnum.PLATFORM)) {
                                couponInfo = c;
                            }
                        }
                        if (couponMap.containsKey(couponInfo.getBatchId())) {
                            couponInfo.setBudgetUnId(couponMap.get(couponInfo.getBatchId()));
                        }
                    }
                    platDiscount.setCouponInfo(couponInfo);
                    platDiscount.setSubOrderId(subOrderId);
                    return platDiscount;
                })
                .collect(Collectors.toMap(PlatDiscount::getSubOrderId, PlatDiscount::getCouponInfo, (k1, k2) -> k1));

        Map<Long, OtherInfo> otherInfoMap = otherList.stream()
                .map(OtherInfo::new)
                .collect(Collectors.toMap(OtherInfo::getSubOrderId, Function.identity(), (k1, k2) -> k1));

        System.out.println(new Date());
        List<String> dataList = orderList.parallelStream()
                .map(line -> {
                    DataInfo dataInfo = new DataInfo();
                    String[] arrays = line.split("\t");
                    dataInfo.setOrderId(arrays[0]);
                    Long subOrderId = Long.valueOf(arrays[1]);
                    dataInfo.setSubOrderId(subOrderId);
                    CouponInfo couponInfo = platDiscountMap.get(subOrderId);
                    dataInfo.setFinishTime(arrays[3]);
                    dataInfo.setShopId(arrays[4]);
                    dataInfo.setProductId(arrays[5]);
                    dataInfo.setGmv(arrays[6]);
                    dataInfo.setZeroAdjustPrice(arrays[7]);
                    if (couponInfo != null) {
                        dataInfo.setBatchId(couponInfo.getBatchId());
                        dataInfo.setBudgetUnId(couponInfo.getBudgetUnId());
                        dataInfo.setSplitAmount(couponInfo.getSplitAmount());
                    }

                    OtherInfo otherInfo = otherInfoMap.get(subOrderId);
                    if (otherInfo != null) {
                        dataInfo.setPayTime(otherInfo.getPayTime());
                        dataInfo.setCompanyName(otherInfo.getCompanyName());
                        dataInfo.setProductName(otherInfo.getProductName());
                        dataInfo.setShopName(otherInfo.getShopName());
                    }

                    return dataInfo.getOrderId() + " " +
                            dataInfo.getSubOrderId() + " " +
                            dataInfo.getPayTime() + " " +
                            dataInfo.getFinishTime() + " " +
                            dataInfo.getCompanyName() + " " +
                            dataInfo.getShopId() + " " +
                            dataInfo.getShopName() + " " +
                            dataInfo.getProductId() + " " +
                            dataInfo.getProductName() + " " +
                            dataInfo.getGmv() + " " +
                            dataInfo.getZeroAdjustPrice() + " " +
                            dataInfo.getBatchId() + " " +
                            dataInfo.getSplitAmount() + " " +
                            dataInfo.getBudgetUnId();
                })
                .collect(Collectors.toList());

        System.out.println(new Date());
        FileUtils.writeLines(dataList, FILE_RESULT, false);
    }

    private static PlatDiscount getDiscountInfo(String line) {
        PlatDiscount platDiscount = new PlatDiscount();
        String[] arrays = line.split("\t");
        Long subOrderId = Long.valueOf(arrays[0]);
        DiscountInfo discountInfo = JsonUtils.jsonToObject(arrays[1], DiscountInfo.class);
        List<CouponInfo> couponInfoList = discountInfo.getCouponInfoList();
        CouponInfo couponInfo = new CouponInfo();
        for (CouponInfo c : couponInfoList) {
            if (c.getCouponBizType().equals(CouponBizTypeEnum.PLATFORM)) {
                couponInfo = c;
            }
        }
        platDiscount.setSubOrderId(subOrderId);
        platDiscount.setCouponInfo(couponInfo);
        return platDiscount;
    }
}

@Data
class PlatDiscount {
    private Long subOrderId;

    private CouponInfo couponInfo;
}

@Data
class OtherInfo {
    private Long subOrderId;

    private String payTime;

    private String companyName;

    private String shopName;

    private String productName;

    public OtherInfo() {
    }

    public OtherInfo(String line) {
        String[] arrays = line.split("\t");
        int length = arrays.length;
        this.subOrderId = Long.valueOf(arrays[0]);
        this.payTime = arrays[1] == null ? "" : arrays[1];
        this.companyName = arrays[2] == null ? "" : arrays[2];
        this.shopName = arrays[3] == null ? "" : arrays[3];
        if (length == 5) {
            this.productName = arrays[4] == null ? "" : arrays[4];
        }
    }
}

@Data
class DataInfo {

    private String orderId;

    private Long subOrderId;

    private String payTime;

    private String finishTime;

    private String companyName;

    private String ShopId;

    private String shopName;

    private String productId;

    private String productName;

    private String gmv;

    private String zeroAdjustPrice;

    /**
     * 券批次id
     */
    private String batchId;

    /**
     * 下单分摊金额
     */
    private String splitAmount;

    private String budgetUnId;

    public String getOrderId() {
        return orderId;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public String getPayTime() {
        return payTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getShopId() {
        return ShopId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getGmv() {
        return gmv;
    }

    public String getZeroAdjustPrice() {
        return zeroAdjustPrice;
    }

    public String getBatchId() {
        return batchId;
    }

    public String getSplitAmount() {
        return splitAmount;
    }

    public String getBudgetUnId() {
        return budgetUnId;
    }
}
