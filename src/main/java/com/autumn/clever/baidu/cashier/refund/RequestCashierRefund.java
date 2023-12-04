package com.autumn.clever.baidu.cashier.refund;

import com.autumn.clever.baidu.cashier.Constants;
import com.autumn.clever.baidu.cashier.PaySignature;
import com.autumn.clever.goodcoder.JacksonUtil;
import com.google.common.collect.Maps;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @program: sky
 * @description: 请求收银台退款
 * @author: zhangqiuying
 * @create: 2023-08-09 21:20
 **/
@Slf4j
public class RequestCashierRefund {

    // 线上
//    private static String CASHIER_REFUND_URL = "http://refund.trade.baidu-int.com/open/refund/applyOrderRefund";
    // 线下
    private static String CASHIER_REFUND_URL = "http://10.24.3.194:8845/open/refund/applyOrderRefund";

    // 回调地址
    private static String cashierOrderRefundNotifyUrl = "http://refund.trade.baidu-int.com/open/refund/applyOrderRefund";


    public static void main(String[] args) throws UnsupportedEncodingException {
        HttpResponse httpResponse = null;
        try {
            Long nanoTime =  System.nanoTime();
//            Map<String, Object> reqParams = buildReqParams(5, 8176248511L, 97050743137048L, null, 91178915833488L, "MMNbD7", nanoTime, "25", 9900L, 1650L);
            Map<String, Object> reqParams = buildReqParams(5, 2872910650L, 3193552842941L, null, 175149033365L, "My3jZQ", nanoTime, "线下测试单", 2400L, 2400L);
            httpResponse = HttpRequest.post(CASHIER_REFUND_URL).timeout(3000).form(reqParams).send();
            log.info("request_cashier_monitor_refund using: post:[{}], params[{}]", CASHIER_REFUND_URL, reqParams);
        } catch (Exception e) {
            log.error("请求收银台退款失败，httpResponse：{}", JacksonUtil.toJSONString(httpResponse), e);
        }

        String str = "{\"useLimitDTO\":{\"userTotalLimitNum\":10,\"spuUserLimitNum\":5,\"userDayLimitNum\":4,\"spuUserDayLimitNum\":3}}";
        log.info("请求收银台退款成功，httpResponse：{}", JacksonUtil.toJSONString(httpResponse));
    }

    private static Map<String, Object> buildReqParams(Integer appId,
                                                      Long userId,
                                                      Long tpOid,
                                                      Integer refundTriggerType,
                                                      Long payOrderId,
                                                      String appKey,
                                                      Long refundId,
                                                      String refundReason,
                                                      Long payMoney,
                                                      Long refundAmount) throws UnsupportedEncodingException {
        Map<String, Object> reqParams = Maps.newHashMap();
        // 取的 orderPay site_passport_id: 8523966956
        reqParams.put("userId", userId);
        // 收银台订单id
        reqParams.put("orderId", tpOid);
        // 退款触发类型 1用户发起退款 2业务方客服退款 3业务方服务异常退款
//        reqParams.put("refundType", refundTriggerType);
        // 支付单id orderPay 的 tp_oid
        reqParams.put("tpOrderId", payOrderId);
        // orderUcCashier 的 appKey，直接取 orderPay 里面的 appKey 字段
        reqParams.put("appKey", appKey);
        // 退款单id
        reqParams.put("bizRefundBatchId", refundId.toString());
        reqParams.put("isSkipAudit", "1");
        //
        reqParams.put("refundReason", URLEncoder.encode(refundReason, Constants.CHARSET_UTF8));
//        if (StringUtils.isNoneBlank(cashierOrderRefundNotifyUrl)) {
//            reqParams.put("refundNotifyUrl", cashierOrderRefundNotifyUrl);
//        }

        // orderRefund.getRefundAmount().equals(orderPay.getPayMoney().longValue())
        if (!refundAmount.equals(payMoney)) { // 部分退
            reqParams.put("applyRefundMoney", refundAmount);
        }

        reqParams.put("refundReason", URLEncoder.encode(refundReason, Constants.CHARSET_UTF8));

        OrderPayChannel payChannel = getOrderPayChannel();
        reqParams.put("rsaSign", genSignWithRsaObj(reqParams, payChannel.getPrivateKey()));

        return reqParams;
    }

    private static OrderPayChannel getOrderPayChannel() {
        OrderPayChannel payChannel = new OrderPayChannel();
        payChannel.setPrivateKey("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANUENzMyVbHdK2kHJ4jXIaLx1Q33DRgKZRm5Kks/YlQmK6inKDq4nbTd3VAU5YDGcUua1QSe+H8IbhGcYjZ00eFqBY1yIQj41KoDxMHafhfCQpKnkWIkVEbefFLGZwDT6zNh/TbBB1wHuqXyJOAm/drbZIRNZaPPnq8P/QZifMWzAgMBAAECgYEAhxYyT2RXVgF1WxivaKdNbIFCZKXMhvdooAR+HtQmbkj6nOzMpViYoq+kfvmRhbXS+WhKOLD708uryb1Lx08UHV8giIIyUYLDdY3shbVLICp0IF0mTuc4TTQ69xEt+YxUGYLsGL/4xQrCBi8oxGi5LvGrfApAbU1iXKd5l4hFtfkCQQD92HoF4/rNpBKdqyjkju5GvdXM9xZD7adMNT6Qhh3M97IPBC8+2HPio/YfQ2TGNAR9IwbV4rOf/Ai/Gv9n9yl1AkEA1tMH1pXd1v5/2SYfTZrqAQhbjHa6/JktyzQt7Q2SBSYKKPK2KgYGe+IobZFRMBSl5JN20Opd/qsecR7wkIglhwJBAITR5w77+bftuHvdGdl1XAGRxLOpOXIzgEzksybFtgQQOo+W3e2hLgmRRu+WwLzgbNTZJfN12gzEwALWansInR0CQFRJxL+LWdlrTdpzvMCEUeBJ2THJvCA0MeWS+ZoTQwHGJSJf6N2gM5cT62ziIYrN7zE6MtpRusObfHxB+X2cDa0CQC2H4gelpbi8u1nKCusopBRtn6XpwB4Jrcji00m6Df15Ahcjgfrxp8R0MAIpTuwPmsxpEArLihTiPt0lIMvDs8c=");
        return payChannel;
    }

    public static Object genSignWithRsaObj(Map<String, Object> reqParams, String privateKey) {
        String sortedParamsContent = getSignContentObj(reqParams);
        String rsaSign = PaySignature.rsaSign(sortedParamsContent, privateKey, "UTF-8");
        log.info("sign detail: sorted params=[{}]  rsa sign=[{}]", sortedParamsContent, rsaSign);
        return rsaSign;
    }

    /**
     * 获取拼接字符串
     *
     * @param reqParams
     * @return
     */
    private static String getSignContentObj(Map<String, Object> reqParams) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<>(reqParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (String key : keys) {
            String value = reqParams.get(key).toString();
            content.append(index == 0 ? "" : "&").append(key).append("=").append(value);
            index++;
        }
        return content.toString();
    }
}
