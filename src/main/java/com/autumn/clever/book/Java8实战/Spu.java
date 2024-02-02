package com.autumn.clever.book.Java8实战;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2024-01-24 20:30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spu {

    private int salePrice;

    private int stock;

    private int restStock;


}
