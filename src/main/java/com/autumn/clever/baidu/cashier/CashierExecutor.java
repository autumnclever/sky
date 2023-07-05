package com.autumn.clever.baidu.cashier;

import com.autumn.clever.common.CommonUtils;
import com.autumn.clever.common.Constants;
import com.autumn.clever.common.JsonUtils;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.autumn.clever.common.Constants.CHARSET_UTF8;

/**
 * @program: sky
 * @description: 百度收银台request
 * @author: zhangqiuying
 * @create: 2023-06-16 11:34
 **/
@Slf4j
public class CashierExecutor {

    /**
     * 请求百度收银台服务
     *
     * @param requestUrl    服务URL
     * @param requestParams 服务参数
     * @return T
     */
    public static <T> T requestCashier(String requestUrl, Map<String, Object> requestParams, Class<T> tClass) {
        String response = null;
        String errorResponse = null;
        long cost = 0;
        T responseDto = null;
        try {
            long start = System.currentTimeMillis();
            HttpResponse httpResponse = HttpRequest
                    .post(requestUrl)
                    .timeout(Constants.HTTP_TIMEOUT)
                    .form(requestParams)
                    .send();
            cost = System.currentTimeMillis() - start;
            log.info("request_cashier_monitor using:{}ms, post:[{}], params[{}]",
                    cost, requestUrl, requestParams);

            if (httpResponse.statusCode() == Constants.HTTP_OK) {
                response = httpResponse.charset(CHARSET_UTF8).bodyText();
            } else {
                errorResponse = httpResponse.charset(CHARSET_UTF8).bodyText();
            }

            if (response != null && !response.isEmpty()) {
                responseDto = JsonUtils.jsonToObject(response, tClass);
                if (CommonUtils.checkNotNull(responseDto)) {
                    log.info("request_cashier_success, post: [{}], params: [{}], response:[{}]",
                            requestUrl, requestParams, response);
                    return responseDto;
                }
                log.error("cashier_request_transform Exception, post: [{}], params: [{}], response:[{}]",
                        requestUrl, requestParams, response);
            } else {
                log.error("cashier_request_error Exception, post: [{}], params: [{}], response:[{}]",
                        requestUrl, requestParams, errorResponse);
            }
        } catch (Exception e) {
            log.error("cashier_request_error Exception, post: [{}], params: [{}], error[{}]",
                    requestUrl, requestParams, e);
            log.error("cashier_request_error2 Exception, post: [{}], params: [{}]",
                    requestUrl, requestParams, e);
        }
        return null;
    }
}
