package com.autumn.clever.baidu.jdk17升级;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-12-07 12:14
 **/
public class StrUtilFix {

    private static final String SELECT_SQL = "select id from Idonly.genid where type_name = {} limit {}";

    private static final String SELECT_SQL2 = "select id from Idonly.genid where type_name = %s limit %d";

    /**
     * 批量获取唯一id的数量
     */
    private static final Integer BATCH_GENERATE_COUNT = 100;

    public static void main(String[] args) {
        String type = "1";
        String str = StrUtil.format(SELECT_SQL, type, BATCH_GENERATE_COUNT);
//        atomIdList = CollUtil.newArrayList(CollUtil.newHashSet(ids));
        System.out.println(str);
        String str2 = String.format(SELECT_SQL2, type, BATCH_GENERATE_COUNT);
        System.out.println(str2);
    }
}
