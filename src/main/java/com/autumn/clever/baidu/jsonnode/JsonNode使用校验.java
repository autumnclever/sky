package com.autumn.clever.baidu.jsonnode;

import com.autumn.clever.baidu.营销的一些实体类.PromotionInfo;
import com.autumn.clever.goodcoder.JacksonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.TextNode;
import org.apache.commons.lang.StringUtils;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-11-07 15:01
 **/
public class JsonNode使用校验 {

    public static void main(String[] args) {
        PromotionInfo promotionInfo = new PromotionInfo();
        String extraInfo = "{\"businessTag\":\"Ov2\",\"eventTs\":1700189062,\"info\":{\"actType\":6,\"activityID\":233,\"activityName\":\"双十一百亿直补\",\"endTime\":1702780072,\"startTime\":1700189800,\"tag\":\"百亿直补\"}}";
//        String extraInfo = "{\"businessTag\":\"Ov2\",\"eventTs\":1699004497,\"info\":{\"actType\":6,\"activityID\":66,\"activityName\":\"双11商城直补活动场次\",\"endTime\":1699322339,\"platformActivityType\":1,\"platformSessionTag\":2,\"promotionAmount\":10000,\"promotionThreshold\":70000,\"startTime\":1699149540,\"tag\":\"百亿直补\"}}";
        promotionInfo.setExtraInfo(extraInfo);
        System.out.println(isTenBillionAllowancePromotion(promotionInfo));
    }

    public static boolean isTenBillionAllowancePromotion(PromotionInfo promotionInfo) {
        if (promotionInfo == null
                || StringUtils.isBlank(promotionInfo.getExtraInfo())) {
            return false;
        }

        // extraInfo 字段纯透传，coco、grape 无实体类定义，直接用 JsonNode 解析
        JsonNode extraJsonNode = JacksonUtil
                .parseObject(promotionInfo.getExtraInfo(), JsonNode.class);
        if (extraJsonNode == null || extraJsonNode.get("info") == null) {
            return false;
        }
        JsonNode infoJsonNode = extraJsonNode.get("info");
        JsonNode tagJsonNode = infoJsonNode.get("tag");
        if (tagJsonNode == null) {
            return false;
        }
        JsonNodeType jsonNodeType = tagJsonNode.getNodeType();
        String typeName = jsonNodeType.name();
        System.out.println("typeName = " + typeName);
        TextNode textNode = (TextNode) tagJsonNode;
        String text = textNode.textValue();
        return text.equals("百亿直补");
    }
}
