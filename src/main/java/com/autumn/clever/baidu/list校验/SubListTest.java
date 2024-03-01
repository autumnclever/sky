package com.autumn.clever.baidu.list校验;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2024-02-18 14:57
 **/
public class SubListTest {

    private static ObjectMapper objectMapper;

    public static void main(String[] args) {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        List<Integer> list2 = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        List<Integer> list3 = Lists.newArrayList();

//        List<Long> newScopeIdList = Lists.newArrayList(1L, 2L, 3L, 4L, 5L, 6L);
//        List<Long> oldScopeIdList = Lists.newArrayList(1L, 2L, 3L, 4L, 5L, 6L);
//
//        List<Long> secondaryIdList = Lists.newArrayList(1694479L, 1694479L, 1694479L, 2152906L, 2152906L, 2152906L, 22010604L, 22010604L, 20018016444L, 20018016444L, 20018016444L, 20018016444L, 20018016444L, 20018016444L, 20018016444L, 20018016444L, 20018016444L, 20018016444L, 20018016444L, 20018016444L, 20018016444L, 20018016444L, 20078089004L, 20100239022L, 20100239022L, 20100239022L, 20100239022L, 20141589759L, 20141595694L, 20141595694L, 20141595694L, 20141595694L, 20141595694L, 20141595694L, 20141595694L, 20141595694L, 20174590858L, 20175282374L, 20200850232L, 20242309971L, 20243340189L, 20243340189L, 20243340189L, 20243340189L, 20243340189L, 20245988532L, 20260997766L, 20261533252L, 20261801120L, 20261801120L, 20283660832L, 20283876929L, 20286157313L, 20286908171L, 20286974087L, 20286974087L, 20286974087L, 20286974087L, 20286984132L, 20286984134L, 20286984136L, 20286985132L, 20286985134L, 20286988724L, 20286988724L, 20286988724L, 20286988724L, 20286988724L, 20286988724L, 20287010062L, 20287010062L, 20287010062L, 20287010062L, 20287279791L, 20287279791L, 20287357727L, 20287393919L, 20287402412L, 20287719480L, 20288046704L, 20288046704L, 20288046704L, 20288046704L, 20288046704L, 20288046704L, 20288046704L, 20288046704L, 20288046704L, 20288313155L, 7708149L, 7708149L, 11086374L, 11344184L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 11352207L, 36501153L, 20037916214L, 20037916214L, 20037916214L, 20037916214L, 20043884002L, 20064265400L, 20064265400L, 20082532732L, 20082532732L, 20104684712L, 20138051738L, 20140469257L, 20140469257L, 20141580691L, 20141580691L, 20141580691L, 20163301498L, 20167575878L, 20175433058L, 20210900956L, 20210900956L, 20210900956L, 20219655040L, 20221306080L, 20237122332L, 20237122332L, 20237122332L, 20238837136L, 20238837148L, 20238837148L, 20238837148L, 20239123942L, 20250138460L, 20250138464L, 20250138466L, 20259853807L, 20282307850L, 20283808029L, 20285028614L, 20285050256L, 20285586395L, 20285966925L, 7674436L, 7708471L, 7708471L, 11345126L, 11345126L, 11345323L, 11345323L, 11354170L, 11354170L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 11356324L, 12410547L, 33160731L, 33160731L, 33160731L, 33160731L, 35782697L, 36446780L, 37841853L, 20019378926L, 20019378926L, 20019378926L, 20019378926L, 20026623092L, 20030183910L, 20030889086L, 20037907629L, 20037907629L, 20042339059L, 20055688105L, 20055688105L, 20068240854L, 20072600093L, 20072600093L, 20078104609L, 20078104609L, 20124412810L, 20124416121L, 20126342434L, 20132610898L, 20132610898L, 20138206700L, 20139905134L, 20140399812L, 20141166132L, 20141166132L, 20141319731L, 20141581078L, 20141581078L, 20141581078L, 20157013586L, 20163326098L, 20164205679L, 20171183041L, 20171183041L, 20171183041L, 20171183041L, 20171183041L, 20171745637L, 20171745637L, 20171745637L, 20171745637L, 20191061835L, 20198332891L, 20201992669L, 20204906184L, 20223876249L, 20223876249L, 20238820544L, 20238820544L, 20238820544L, 20239097124L, 20239097124L, 7679362L, 7683305L, 7683305L, 11363149L, 11363149L, 19569148L, 32248477L, 32248477L, 35331258L, 35331258L, 35331258L, 35331258L, 35331258L, 35331258L, 35331258L, 35331258L, 20018006603L, 20019003009L, 20033471232L, 20033471232L, 20033471232L, 20033471232L, 20033471232L, 20033471232L, 20033471232L, 20033471232L, 20033471232L, 20039927698L, 20039927698L, 20039927698L, 20042635029L, 20042635029L, 20042635029L, 20043809304L, 20043809304L, 20047949120L, 20063988133L, 20063988133L, 20063988133L, 20064566554L, 20064566554L, 20068102070L, 20068102070L, 20068102070L, 20068102070L, 20070613849L, 20070613849L, 20077592827L, 20077592827L, 20087193232L, 20123755234L, 20123755234L, 20126342432L, 20134011495L, 20136335533L, 20136865142L, 20136969483L, 20137129137L, 20141172441L, 20141593795L, 20141593795L, 20141593795L, 20141601362L, 20149654936L, 20152252332L, 20152252338L, 20165599199L, 20170024364L, 20175719632L, 20179103310L, 20179103310L, 20179103310L, 20182613003L, 20200834749L, 20200834749L, 20200834749L, 20200834749L, 20200834749L, 20200834749L, 20200834749L, 20200834749L, 20200834749L, 20200834749L, 20200848038L, 20200848044L, 20212607032L, 20238837140L, 20238837144L, 20238837144L, 20238837144L);
//
//        Map<Long, List<Long>> map = new HashMap<>();
//
//        Map<Long, List<Long>> map1 = new HashMap<>();
//        Map<Long, List<Long>> map2 = new HashMap<>();
//
//
//        map1.put(1L, newScopeIdList);
//        map2.put(2L, oldScopeIdList);
//
//        map.putAll(map1);
//        map.putAll(map2);
//
//        System.out.println("map:" + map);

        Set<Integer> commonSet =
                Sets.intersection(Sets.newHashSet(list1), Sets.newHashSet(list3));
        System.out.println("commonSet:" + commonSet);


//        List<Long> secondaryIdList2 = secondaryIdList.stream().distinct().collect(Collectors.toList());
//        System.out.println("secondaryIdList2:" + secondaryIdList2);
//
//        List<List<Long>> idLists = Lists.partition(secondaryIdList2, 50);
//        for (List<Long> idList : idLists) {
//            System.out.println("idList:" + idList);
//        }

//                List<Long> deleteScopeIdList = new ArrayList<>(CollectionUtils.subtract(oldScopeIdList, newScopeIdList));
//        // 2.2 计算需要新增的商品
//        List<Long> addScopeIdList = new ArrayList<>(CollectionUtils.subtract(newScopeIdList, oldScopeIdList));
//        // 2.3 计算有秒杀价格、库存、用户限购数量变更的商品，取交集
//        List<Long> existScopeIdList = new ArrayList<>(CollectionUtils.intersection(newScopeIdList, oldScopeIdList));
//        System.out.println("deleteScopeIdList:" + deleteScopeIdList);
//        System.out.println("addScopeIdList:" + addScopeIdList);
//        System.out.println("existScopeIdList:" + existScopeIdList);
//
//        System.out.println("list1:" + toJSONString(list1));
//        System.out.println(list1);
//        List<Integer> list2 = paging(list1, 2, 5);
//        System.out.println(list1.size());
//        System.out.println(list2);


//        // 4.参加秒杀的商品信息
//        List<FlashSaleSpuDTO> flashSaleSpuList = list1
//                .stream()
//                .map(spuId -> ProductFlashSaleConvertUtil
//                        .buildFlashSaleSpuDTO(promotionScopeMap.get(spuId), productDetailMap.get(spuId)))
//                .collect(Collectors.toList());


    }

    public static String toJSONString(Object obj) {
        try {
            if (obj == null) {
                return null;
            }
            return objectMapper.writeValueAsString(obj);
        } catch (Throwable e) {
//            log.error("JacksonUtil_toJSONString_exception CocoException", e);
        }
        return null;
    }

    public static <T> List<T> paging(List<T> list, int pageNum, int pageSize) {
        List<T> pagingList = Lists.newArrayList();
        int totalSize = list.size();
        // 如果分页大小 大于等于总个数 无需进行内存分页
        if (pageSize > totalSize) {
            pagingList.addAll(list);
            return pagingList;
        }
        int totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        if (pageNum > totalPage) {
            return pagingList;
        }
        int startIndex = (pageNum - 1) * pageSize;
        System.out.println("startIndex:" + startIndex);
        int endIndex = startIndex + pageSize;
        System.out.println("endIndex:" + endIndex);
        if (totalSize <= endIndex) {
            endIndex = totalSize;
        }
        System.out.println("list1:" + list);
        pagingList.addAll(list.subList(startIndex, endIndex));
        System.out.println("list1:" + list);
        System.out.println("pagingList:" + pagingList);
        return pagingList;
    }
}
