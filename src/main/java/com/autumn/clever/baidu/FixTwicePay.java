package com.autumn.clever.baidu;

import com.autumn.clever.baidu.cashier.CashierExecutor;
import com.autumn.clever.baidu.cashier.CashierOrderSyncResponseDto;
import com.autumn.clever.baidu.cashier.PayCallbackResponse;
import com.autumn.clever.baidu.cashier.SyncCashierSubOrderDto;
import com.autumn.clever.common.JsonUtils;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: sky
 * @description: 修复电商订单支付两次问题
 * @author: zhangqiuying
 * @create: 2023-06-16 11:28
 **/
public class FixTwicePay {
    public static String getRsaSignForTest(String orderId,
                                           String tpOrderId,
                                           String orders,
                                           String appKey,
                                           String dealId, String privateKey) {
        Map<String, String> signParams = Maps.newHashMap();
        signParams.put("orderId", orderId);
        signParams.put("tpOrderId", tpOrderId);
        signParams.put("orders", orders);
        signParams.put("appKey", appKey);
        signParams.put("dealId", dealId);
//        return PaySignature.genSignWithRsa(signParams, privateKey);
        return null;
    }

    public static Map<String, Object> buildRequestParamMap() {
        // 构造子订单信息
        SyncCashierSubOrderDto syncCashierSubOrderDto = new SyncCashierSubOrderDto();
        syncCashierSubOrderDto.setTpOrderId("61195489753419");
        syncCashierSubOrderDto.setTotalMoney(200000L);
        syncCashierSubOrderDto.setAppKey("MMUoSi");
        syncCashierSubOrderDto.setDealId(922850596L);
        syncCashierSubOrderDto.setTpMarketingDetail("");
        syncCashierSubOrderDto.setIsConsumed(PayCallbackResponse.ConsumeStatus.NOCONSUMED.value());
        syncCashierSubOrderDto.setSplitMoney(198800L);
        syncCashierSubOrderDto.setSplitRatio("0");

        List<SyncCashierSubOrderDto> syncCashierSubOrderList = new ArrayList<>();
        syncCashierSubOrderList.add(syncCashierSubOrderDto);
        // 组装收银台请求参数
        String orders = JsonUtils.toJson(syncCashierSubOrderList);
        Map<String, Object> requestParam = new HashMap<>();

        Long orderId = 97114732893347L;
        Long tpOrderId = 91196153813419L;
        String appKey = "MMUKPa";
        String dealId = "1359394905";
        requestParam.put("orderId", orderId);
        requestParam.put("tpOrderId", tpOrderId);
        requestParam.put("orders", orders);
        requestParam.put("appKey", appKey);
        requestParam.put("dealId", dealId);


        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALoj8vENGRucsuSGMm/rcsgd"
                + "+NUl3onPyd1jIY332xJkKvjZEnEnb4ZsRmFT7c2KoWeb3+KeWAL88iJ0gxPL3QgPcGos/bWS29JTjUOWA1esJfMQSgKBsqu"
                + "/euSnKjF5c5QC0hvPXnFlXCMoDtpprIxI9Z1C8O+i6Va3Y4gu8mTTAgMBAAECgYB9jXP3rbsKyeZMiEwK+8g"
                + "/PSAzPUjesd5aStw+Mzl8LT5i/aAX3N63z96lFYRoSKLYDOa1p9J7qXv1S5uCRdb+yxXi"
                + "+67d06FfHs2cdWVfUjTWiIgyXQHbKVDH7oc/4BcygxKCP9thRlVfykfRJhsjtPFBC42XSmvx4N5xcKS28QJBAOOTGMnYPQCjXf"
                + "/lQLOBjGlXqCkN4SjETjSSJUjrymCAr6jIYTa8ZqoDDMgaiWAz/AV0bIwPfIdPQjuxCfmsOPkCQQDRY"
                + "/UGo3wLAyFmzbXhEOPwkGdOlJXtyW8WxdS5a1uDWZ/YllHrDLiJ+uSPmFUaVVBGuA/eKJ/YQqvz"
                + "/vhEyysrAkEAtZa8YuMe2hGBgh0mIZYvuibt0cR3c5YgaSZsheFP3O0SPXWWzJxMt0AzxMAX+iHA6YFa0"
                + "+/PiBCgYw0DTqwz0QJARoeP0iOhNahhSNqDqALjdHV/hrV/5u+Rzq7mX2ptEcpPkEnZ3"
                + "/2R71AmSsP57nvMJXX8Sk2Owe57Mx2wDKQOrwJAE8100wg1Q6dU3vsdvJ7rRlWnoTHOnkgaRY"
                + "+YcLPMmyA9dAekSiCF52ETkREN6lfWrI0r/idF7pqRMUUbKLxUDA==";

        requestParam.put("rsaSign", getRsaSignForTest(
                String.valueOf(orderId),
                String.valueOf(tpOrderId),
                orders,
                appKey,
                dealId,
                privateKey));
        return requestParam;
    }

    public static void main(String[] args) {
        // 推子订单
        CashierOrderSyncResponseDto cashierOrderSyncResponseDto = CashierExecutor.requestCashier(
                "http://trade.baidu-int.com/order/syncSuborders",
                buildRequestParamMap(),
                CashierOrderSyncResponseDto.class);
        System.out.println("finish");
    }
}
