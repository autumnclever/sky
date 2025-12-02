/**
 * Copyright (C), 2024-2025, baidu
 */
package com.autumn.clever.java8;

import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * FileName: anyMatchDemo
 * Author:   zhangqiuying
 * Date:     2025/6/3 21:52
 * Description:
 */
public class anyMatchDemo {

    private static final Integer PACKAGE_TYPE_SPECIAL_LIVE = 1;

    public static void main(String[] args) {
//        LockPromotionDiscountPackageDTO lockPackageDTO1 = new LockPromotionDiscountPackageDTO(1);
//        LockPromotionDiscountPackageDTO lockPackageDTO2 = new LockPromotionDiscountPackageDTO(0);
//        LockPromotionDiscountPackageDTO lockPackageDTO3 = new LockPromotionDiscountPackageDTO(1);
//
//        List<LockPromotionDiscountPackageDTO> lockPackageDTOList = new ArrayList<>();
////        lockPackageDTOList.add(lockPackageDTO1);
//        lockPackageDTOList.add(lockPackageDTO2);
////        lockPackageDTOList.add(lockPackageDTO3);
//
//        boolean isUseSpecialLivePackage = lockPackageDTOList.stream().anyMatch(
//                lockPackageDTO -> PACKAGE_TYPE_SPECIAL_LIVE.equals(lockPackageDTO.getPackageType()));
//
//        System.out.println(isUseSpecialLivePackage);

        Date expireTime = new Date(1749096240L);
        long time = calcDiscountPackageExpireTime(expireTime);
        System.out.println(time);
    }

    private static long calcDiscountPackageExpireTime(Date expireTime) {
        return (expireTime.getTime() - new Date(1749096120L).getTime()) / 1000 + 60 * 60;
    }
}

class LockPromotionDiscountPackageDTO {
    /**
     * 优惠包类型
     */
    private Integer packageType;

    public Integer getPackageType() {
        return packageType;
    }

    public void setPackageType(Integer packageType) {
        this.packageType = packageType;
    }

    public LockPromotionDiscountPackageDTO(Integer packageType) {
        this.packageType = packageType;
    }
}

