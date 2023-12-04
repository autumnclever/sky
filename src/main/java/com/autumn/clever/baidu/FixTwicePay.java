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
        syncCashierSubOrderDto.setTpOrderId("93016574423456");
        syncCashierSubOrderDto.setTotalMoney(9900L);
        syncCashierSubOrderDto.setAppKey("MM5yVh");
        syncCashierSubOrderDto.setDealId(235048387L);
        syncCashierSubOrderDto.setTpMarketingDetail("");
        syncCashierSubOrderDto.setIsConsumed(PayCallbackResponse.ConsumeStatus.NOCONSUMED.value());
        syncCashierSubOrderDto.setSplitMoney(198800L);
        syncCashierSubOrderDto.setSplitRatio("0");

        syncCashierSubOrderDto.setTpOrderId("93016963503456");
        syncCashierSubOrderDto.setTotalMoney(9900L);
        syncCashierSubOrderDto.setAppKey("MM5yVh");
        syncCashierSubOrderDto.setDealId(235048387L);
        syncCashierSubOrderDto.setTpMarketingDetail("");
        syncCashierSubOrderDto.setIsConsumed(PayCallbackResponse.ConsumeStatus.NOCONSUMED.value());
        syncCashierSubOrderDto.setSplitMoney(198800L);
        syncCashierSubOrderDto.setSplitRatio("0");

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

        Long orderId = 102766990958632L;
        Long tpOrderId = 93017073013456L;
        String appKey = "MM5yVh";
        String dealId = "235048387";
        requestParam.put("orderId", orderId);
        requestParam.put("tpOrderId", tpOrderId);
        requestParam.put("orders", orders);
        requestParam.put("appKey", appKey);
        requestParam.put("dealId", dealId);


        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANUENzMyVbHdK2kHJ4jXIaLx1Q33DRgKZRm5Kks/YlQmK6inKDq4nbTd3VAU5YDGcUua1QSe+H8IbhGcYjZ00eFqBY1yIQj41KoDxMHafhfCQpKnkWIkVEbefFLGZwDT6zNh/TbBB1wHuqXyJOAm/drbZIRNZaPPnq8P/QZifMWzAgMBAAECgYEAhxYyT2RXVgF1WxivaKdNbIFCZKXMhvdooAR+HtQmbkj6nOzMpViYoq+kfvmRhbXS+WhKOLD708uryb1Lx08UHV8giIIyUYLDdY3shbVLICp0IF0mTuc4TTQ69xEt+YxUGYLsGL/4xQrCBi8oxGi5LvGrfApAbU1iXKd5l4hFtfkCQQD92HoF4/rNpBKdqyjkju5GvdXM9xZD7adMNT6Qhh3M97IPBC8+2HPio/YfQ2TGNAR9IwbV4rOf/Ai/Gv9n9yl1AkEA1tMH1pXd1v5/2SYfTZrqAQhbjHa6/JktyzQt7Q2SBSYKKPK2KgYGe+IobZFRMBSl5JN20Opd/qsecR7wkIglhwJBAITR5w77+bftuHvdGdl1XAGRxLOpOXIzgEzksybFtgQQOo+W3e2hLgmRRu+WwLzgbNTZJfN12gzEwALWansInR0CQFRJxL+LWdlrTdpzvMCEUeBJ2THJvCA0MeWS+ZoTQwHGJSJf6N2gM5cT62ziIYrN7zE6MtpRusObfHxB+X2cDa0CQC2H4gelpbi8u1nKCusopBRtn6XpwB4Jrcji00m6Df15Ahcjgfrxp8R0MAIpTuwPmsxpEArLihTiPt0lIMvDs8c=";

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
