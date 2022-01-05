package com.autumn.clever.grape;

import java.util.List;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/12/20 4:33 下午
 */
public class RedisKeyProperties {

    private List<RedisKeyAlias> keys;

    /**
     * 通过名称和appId获取redisKey
     *
     * @param name      redis key 名称
     * @param appIdEnum 业务线
     * @return 没有匹配到对应的业务线，返回默认的队列名称
     */
//    public RedisKeyAlias getByKeyNameAndAppId(String name, AppIdEnum appIdEnum) {
//        RedisKeyAlias defaultRedisKeyAlias = null;
//        for (RedisKeyAlias keyAlias : keys) {
//            if (!keyAlias.getName().equals(name)) {
//                continue;
//            }
//            AppIdEnum matchAppId = AppIdEnum.getAppIdEnumByCode(keyAlias.getAppId());
//            // 匹配到了对应的业务线
//            if (matchAppId == appIdEnum) {
//                return keyAlias;
//            }
//            // 默认的队列名称
//            if (matchAppId == null) {
//                defaultRedisKeyAlias = keyAlias;
//            }
//        }
//        // 没有匹配到对应的业务线，返回默认的队列名称
//        return defaultRedisKeyAlias;
//    }
}
