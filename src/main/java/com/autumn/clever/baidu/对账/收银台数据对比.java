package com.autumn.clever.baidu.对账;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-09-04 11:11
 **/
@Slf4j
public class 收银台数据对比 {


    public static Long shouyintai_consumed = 0L;
    public static Long shouyintai_refund = 0L;
    public static Long xiaodian_consumed = 0L;
    public static Long xiaodian_refund = 0L;

    public static void main(String[] args) throws IOException {
        String shouyintai = "/Users/zhangqiuying/autumn/sky/src/main/resources/file/august/shouyintai.csv";
        String xiaodian = "/Users/zhangqiuying/autumn/sky/src/main/resources/file/august/augustData3.txt";


        Map<Long, Long> consumeMap = new HashMap<>();
        Map<Long, Long> refundMap = new HashMap<>();
        Stream<String> lines = Files.lines(Paths.get(shouyintai));
        lines.forEach(line -> {
            String[] split = line.split(",");
            Long orderPayId = Long.valueOf(split[3]);
            Long money = Long.valueOf(split[5]);
            Integer type = Integer.valueOf(split[6]);
            if (type == 2) {
                consumeMap.put(orderPayId, money);
                shouyintai_consumed += money;
            } else if (type == 3) {
                refundMap.put(orderPayId, money);
                shouyintai_refund += money;
            } else {
                log("处理收银台文件，类型错误:" + orderPayId);
            }
        });

        // 结果文件
        FileWriter fileWriter = new FileWriter("result.txt", true);
        BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
        try {
            Stream<String> xdLines = Files.lines(Paths.get(xiaodian));
            xdLines.forEach(line -> {
                try {
                    String result = dealLine(line, consumeMap, refundMap);
                    if (!result.equals("")) {
                        bufferWriter.write(result);
                        bufferWriter.newLine();
                    }
                } catch (Exception e) {
                    log(line + " Exception:" + e.getMessage());
                }
            });
            consumeMap.forEach((orderPayId, money) -> {
                String notConsumed = orderPayId + "," + money + "," + "核销-小店不存在";
                try {
                    bufferWriter.write(notConsumed);
                    bufferWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            refundMap.forEach((orderPayId, money) -> {
                String notRefund = orderPayId + "," + money + "," + "退款-小店不存在";
                try {
                    bufferWriter.write(notRefund);
                    bufferWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            log(e.getMessage());
        } finally {
            bufferWriter.close();
        }
        log("shouyintai_consumed:" + shouyintai_consumed);
        log("xiaodian_consumed:" + xiaodian_consumed);
        log("shouyintai_refund:" + shouyintai_refund);
        log("xiaodian_refund:" + xiaodian_refund);
        log("shouyintai:" + (shouyintai_consumed - shouyintai_refund));
        log("xiaodian:" + (xiaodian_consumed - xiaodian_refund));
        log("diff:" + ((shouyintai_consumed - shouyintai_refund) - (xiaodian_consumed - xiaodian_refund)));

    }

    public static String dealLine(String line, Map<Long, Long> consumeMap, Map<Long, Long> refundMap) {
        String[] split = line.split("\t");
        Long orderPayId = Long.parseLong(split[0]);
        Long money = Long.parseLong(split[1]) * 10;
        long type = Long.parseLong(split[2]);
        String resultStr = line;
        if (type == 1) {
            // 核销
            Long consumeMoney = consumeMap.get(orderPayId);
            if (consumeMoney == null) {
                resultStr = resultStr + ",核销-收银台不存在";
            } else {
                xiaodian_consumed += consumeMoney;
                if (consumeMoney.equals(money)) {
//                    resultStr = resultStr + ",核销-ok";
                    resultStr = "";
                } else {
                    resultStr = resultStr + ",核销-金额不对";
                }
                consumeMap.remove(orderPayId);
            }
        } else if (type == 2) {
            // 退款
            Long refundMoney = refundMap.get(orderPayId);
            if (refundMoney == null) {
                resultStr = resultStr + ",退款-收银台不存在";
            } else {
                xiaodian_refund += refundMoney;
                if (refundMoney.equals(money)) {
//                    resultStr = resultStr + ",退款-ok";
                    resultStr = "";
                } else {
                    resultStr = resultStr + ",退款-金额不对";
                }
                refundMap.remove(orderPayId);
            }
        } else {
            log("处理小店文件，类型错误:" + orderPayId);
            resultStr = resultStr + ",处理小店文件-类型错误";
        }
        return resultStr;
    }

    public static void log(Object str) {
        System.out.println(str);
    }

}
