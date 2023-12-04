package com.autumn.clever.baidu.httprequest;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-10-23 18:14
 **/
//@Slf4j
public class OpenApiPush {
//    public void pushOrder (){
//        Map<String, String> data = objectToMap(pushOderInfoDto);
//        Stopwatch sw = Stopwatch.createStarted();
//        try {
//            log.info("第{}次推送订单信息url={},data={}", num, apiUrl, JSON.toJSONString(pushOderInfoDto));
//            HttpHeaders headers = new HttpHeaders();
//            MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded");
//            headers.setContentType(type);
//            MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
//            requestEntity.setAll(data);
//            requestEntity.add("sign", sign);
//            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestEntity, headers);
//            result = restTemplate.postForObject(apiUrl, httpEntity, OpenApiResult.class);
//            log.info("result={}", result);
//            apiReturn = JsonUtils.toJson(result);
//            if (result != null && result.getErrno() != null && result.getErrno().equals(0)) {
//                success = true;
//            }
//        } catch (Exception e) {
//            log.info("openApi_pushData_fail PearbException 第{}次推送订单信息调用接口异常", num, e);
//            apiReturn = e.toString().length() > 1024 ? e.toString().substring(0, 1024) : e.toString();
//        } finally {
//            cost = sw.elapsed(TimeUnit.MILLISECONDS);
//            log.info("第{}次推送订单信息url={},data={},res={}", num, apiUrl, JSON.toJSONString(pushOderInfoDto),
//                    JSON.toJSONString(result));
//            if (num > 1) {
//                log.info("重试推送数据结果 num:{} success:{}", num, success);
//            }
//            num++;
//        }
//    }
}
