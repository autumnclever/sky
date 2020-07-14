package com.autumn.clever.kafka.serializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhangqiuying
 * @Date: 2020/7/14 20:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private String name;
    private String address;
}
