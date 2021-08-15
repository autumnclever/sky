package com.autumn.clever.需求.活动提报;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/14 5:40 下午
 */
public class JackDemo {
    public static void main(String[] args) throws JsonProcessingException {
        ActivitySignUpProductDTO signUpProductDTO1 = new ActivitySignUpProductDTO(1L, 2L, 3L, 1, "sdasda");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(signUpProductDTO1);
        JsonNode jsonNode = mapper.readTree(json);
        System.out.println(jsonNode.asText());
        JsonNode spuNode = jsonNode.get("spuId");
        System.out.println(spuNode.asLong());
        JsonNode skuNode = jsonNode.get("skuId");
        System.out.println(skuNode.asLong());
        JsonNode salePriceNode = jsonNode.get("salePrice");
        System.out.println(salePriceNode.asLong());
        JsonNode saleStockNode = jsonNode.get("saleStock");
        System.out.println(saleStockNode.asInt());
        JsonNode failReasonNode = jsonNode.get("failReason");
        System.out.println(failReasonNode.asText());
    }
}
