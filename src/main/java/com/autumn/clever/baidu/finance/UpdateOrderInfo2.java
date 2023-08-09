package com.autumn.clever.baidu.finance;

import com.autumn.clever.goodcoder.FileUtils;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-08-04 07:34
 **/
public class UpdateOrderInfo2 {

    private static String FILE_PAY_REFUND_OTHER_INFO = "/file/july2/payAndRefundOtherInfo.txt";

    private static String FILE_PAY_REFUND_UPDATE_SQL = "/Users/zhangqiuying/autumn/sky/src/main/resources/file/july/batchUpdateSql200.txt";

    public static void main(String[] args) {

        List<String> lineList = FileUtils.readLinesFromClasspath(FILE_PAY_REFUND_OTHER_INFO);

        List<String> sqlList = lineList.stream().map(line -> {
            String[] arrays = line.split("\t");
            String subOrderId = arrays[0];
            String payTime = arrays[1];
            String companyName = arrays[2];
            String shopName = arrays[3];
            String productName = arrays[4];

            String sql = "update tb_pay_settle_split_info " +
                    "set pay_time = " + payTime + ", company_name = '" + companyName + "', shop_name = '" + shopName + "', product_name = '" + productName +
                    "' where sub_order_id = " + subOrderId + " and charge_type = 2;";
            return sql;
        }).collect(Collectors.toList());

        List<String> batchSqlList = Lists.newArrayList();
        int begin = 0;

        while (begin < sqlList.size()) {
            int end = begin + 200;
            int finalIndex = Math.min(end, sqlList.size());
            String batchSql = "";
            for (int i = begin; i < finalIndex; i++) {
                batchSql += sqlList.get(i);
            }
            batchSqlList.add(batchSql);
            begin = end;
        }

        FileUtils.writeLines(batchSqlList, FILE_PAY_REFUND_UPDATE_SQL, false);
    }
}
