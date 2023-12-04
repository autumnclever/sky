package com.autumn.clever.baidu.对账;

import com.autumn.clever.baidu.StringTools;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-10-01 19:36
 **/
public class 校验一个订单是否是广告单 {

    public static void main(String[] args) {
        String pageUrl = "scene=10910016&spid=&zjopen=1&feedAd=&fid=&bdVid=&shareCuid=undefined&url=undefined";


        Map<String, String> paramMap = StringTools.urlParamsToMap(pageUrl);
        boolean baseCheck = MapUtils.isNotEmpty(paramMap);
        boolean flag = false;
        if (baseCheck &&
                !StringUtils.isAllBlank(
                        paramMap.get(Constants.PAGEURL_SPID),
                        paramMap.get(Constants.PAGEURL_FID))) {
            flag = true;
        }
        if (!flag &&
                baseCheck &&
                StringUtils.isNotBlank(paramMap.get(Constants.ZHIBO_EXT_BDVID)) &&
                !StringUtils.equals(paramMap.get(Constants.ZHIBO_EXT_BDVID), Constants.PAGE_URL_PARAM_UNDEFINED)) {
            flag = true;
        }
        if (!flag &&
                baseCheck &&
                StringUtils.isNotBlank(paramMap.get(Constants.ZHIBO_EXT_BDVID_DXD)) &&
                !StringUtils.equals(
                        paramMap.get(Constants.ZHIBO_EXT_BDVID_DXD),
                        Constants.PAGE_URL_PARAM_UNDEFINED)) {
            flag = true;
        }

        System.out.println(flag);
    }
}
