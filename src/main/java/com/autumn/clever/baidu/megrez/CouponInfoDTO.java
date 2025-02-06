/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu.megrez;

import lombok.Data;

/**
 *
 * FileName: CouponInfoDTO
 * Author:   zhangqiuying
 * Date:     2024/10/10 12:51
 * Description:
 */
@Data
public class CouponInfoDTO {
    private Long id;
    private String batchId;
    private String name;
    private Long ucId;
    private Long shopId;
}
