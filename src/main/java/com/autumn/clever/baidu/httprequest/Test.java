package com.autumn.clever.baidu.httprequest;

import cn.hutool.http.HttpUtil;
import com.autumn.clever.common.JsonUtils;
import com.autumn.clever.goodcoder.FileUtils;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-08-09 00:28
 **/
@Slf4j
public class Test {

    private static String REQUEST_JSON = "/file/requestJson.txt";

    public static void main(String[] args) {
        List<String> list = FileUtils.readLinesFromClasspath(REQUEST_JSON);
//        String requestJson = list.get(0);
        String requestJson = "{\"services\":[{\"type\":0,\"values\":[\"ocpcspamq\"]}],\"req_msg\":{\"type\":0,\"value\":\"sc=two_e_commerce_tag&cuid=_8Hyu0ilSuYL8vfqgavFagurHulPPHavjPSpi_iO-t84kSPp_8vs8L9zA&productid=1008&create_time=2023-08-07 16:36:17&mobile=****\"}}";
//        String requestJson = "{\"services\":[{\"type\":0,\"values\":[\"ocpcspamq\"]}],\"req_msg\":{\"type\":0,\"value\":\"sc=two_e_commerce_tag&cuid=_8Hyu0ilSuYL8vfqgavFagurHulPPHavjPSpi_iO-t84kSPp_8vs8L9zA&productid=1008\"}}";
//        String requestJson = "'{\"services\":[{\"type\":0,\"values\":[\"ocpcspamq\"]}],\"req_msg\":{\"type\":0,\"value\":\"sc=two_e_commerce_tag&cuid=_8Hyu0ilSuYL8vfqgavFagurHulPPHavjPSpi_iO-t84kSPp_8vs8L9zA&productid=1008&create_time=2023-08-07 16:36:17&mobile=****\"}}'";

//        String requestJson = "'{\"services\":[{\"type\":0,\"values\":[\"ocpcspamq\"]}],\"req_msg\":{\"type\":0,\"value\":\"sc=two_e_commerce_tag&cuid=_8Hyu0ilSuYL8vfqgavFagurHulPPHavjPSpi_iO-t84kSPp_8vs8L9zA&productid=1008&create_time=2023-08-07 16:36:17&mobile=****\"}}'";

        String response = "";
        long start = System.currentTimeMillis();
//        String url = "http://10.41.21.155:8015/service/ocpc";
        String url = "http://10.186.91.26:8012/service/ocpc";
        try {
            response = sendPostDataByJson(url, requestJson, "", "UTF-8");
            if (StringUtils.isBlank(response)) {
                System.out.println("false 1");
            }
            JsonNode jsonNode = JsonUtils.parseObject(response);
            if (jsonNode == null || jsonNode.get("err_no") == null) {
                System.out.println("false 2");
            }
            System.out.println("code error");
        } catch (Exception e) {
            String message = String.format("pushBlackUser_fail SniperException, request={}", requestJson);
            System.out.println(message);
        } finally {
            long end = System.currentTimeMillis();
            log.info("pushBlackUser url={}, request={},response={} cost={}", url, requestJson, response,
                    (end - start));
        }
    }

//    public static String sendPostDataByJson2(String url, String json, String token, String encoding){
//        CloseableHttpClient httpClient = (CloseableHttpClient) getHttpClient();
//        HttpPost httpPost = getHttpPost("xxx");
//        StringEntity entity = new StringEntity(request.toString());
//        entity.setChunked(false);
//        httpPost.addHeader("content-type", "application/json");
//        httpPost.setEntity(entity);
//        System.out.println("contentlength = "+String.valueOf(entity.getContentLength()));
//        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost);
//        System.out.println("response = "
//                + response);
//
//        StatusLine statusLine = response.getStatusLine();
//        System.out.println("statusLine = "
//                + statusLine);
//
//        String responseEntity = EntityUtils.toString(response.getEntity());
//        System.out.println("responseEntity = "
//                + responseEntity);
//    }

    public static String sendPostDataByJson(String url, String json, String token, String encoding)
            throws ClientProtocolException, IOException {
        String result = "";

        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.isNotEmpty(token)) {
            httpPost.setHeader("Token", token);
        }
//        httpPost.addHeader("Accept", "*/*");
//        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        // 设置参数到请求对象中
//        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        StringEntity stringEntity = new StringEntity(json, "UTF-8");

//        stringEntity.setChunked(false);
//        stringEntity.setContentEncoding(encoding);
        httpPost.setEntity(stringEntity);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000).build();
        httpPost.setConfig(requestConfig);

        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
//        response.addHeader("");
//        response.addHeader("Access-Allow-Control-Origin", "*");
        System.out.println("response: " + response);
        log.info("response:", response.toString());

        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();

        return result;
    }
}
