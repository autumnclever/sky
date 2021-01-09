package com.autumn.clever.excel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/5 下午12:50
 */
@Service
public class DataCalculate {
    public List<SaleMovieDO> calculateMovieSale2(List<SaleMovieDO> saleMovieDOS, String date) {
        if (CollectionUtils.isEmpty(saleMovieDOS)) {
            return null;
        }
        Map<Integer, List<SaleMovieDO>> saleMoviesMap = Maps.newHashMap();
        saleMovieDOS.stream().forEach(saleMovieDO -> {
            int sort = saleMovieDO.getSort();
            List<SaleMovieDO> saleMovieDOList = saleMoviesMap.get(sort);
            if (CollectionUtils.isEmpty(saleMovieDOList)) {
                saleMovieDOList = Lists.newArrayList();
            }
            saleMovieDOList.add(saleMovieDO);
            saleMoviesMap.put(sort, saleMovieDOList);
        });

        List<SaleMovieDO> saleMovieTotals = Lists.newArrayList();
        saleMoviesMap.keySet().stream().forEach(sort -> {
            List<SaleMovieDO> saleMoviesList = saleMoviesMap.get(sort);

            SaleMovieDO saleMovieTotal = new SaleMovieDO(sort, 0, 0, 0, 0.0, 0.0, date);
            saleMoviesList.stream().forEach(saleMovieDO -> {
                saleMovieTotal.setEntranceUV(saleMovieTotal.getEntranceUV() + saleMovieDO.getEntranceUV());
                saleMovieTotal.setOrderUV(saleMovieTotal.getOrderUV() + saleMovieDO.getOrderUV());
                saleMovieTotal.setPayUV(saleMovieTotal.getPayUV() + saleMovieDO.getPayUV());
                saleMovieTotal.setOrderConversion(saleMovieTotal.getOrderConversion() + saleMovieDO.getOrderConversion());
                saleMovieTotal.setPayConversion(saleMovieTotal.getPayConversion() + saleMovieDO.getPayConversion());
            });
            saleMovieTotals.add(saleMovieTotal);
        });
        return saleMovieTotals;
    }


    public List<SaleMovieDO> calculateMovieSale(List<SaleMovieDO> saleMovieDOS) {
        if (CollectionUtils.isEmpty(saleMovieDOS)) {
            return null;
        }
        Map<Integer, List<SaleMovieDO>> saleMoviesMap = Maps.newHashMap();
        saleMovieDOS.stream().forEach(saleMovieDO -> {
            List<SaleMovieDO> saleMoviesList = saleMoviesMap.get(saleMovieDO.getMovieId());
            if (CollectionUtils.isEmpty(saleMoviesList)) {
                saleMoviesList = Lists.newArrayList();
            }
            saleMoviesList.add(saleMovieDO);
            saleMoviesMap.put(saleMovieDO.getMovieId(), saleMoviesList);
        });

        List<SaleMovieDO> saleMovieTotals = Lists.newArrayList();
        saleMoviesMap.keySet().forEach(movieId -> {
            List<SaleMovieDO> saleMoviesList = saleMoviesMap.get(movieId);
            SaleMovieDO saleMovieTotal = new SaleMovieDO();
            saleMovieTotal.setMovieId(movieId);
            saleMovieTotal.setSort(0);
            saleMovieTotal.setOrderUV(0);
            saleMovieTotal.setEntranceUV(0);
            saleMovieTotal.setPayUV(0);
            saleMovieTotal.setOrderConversion(0.0d);
            saleMovieTotal.setPayConversion(0.0d);
            saleMoviesList.stream().forEach(saleMovieDO -> {
                saleMovieTotal.setDate(saleMovieDO.getDate());
                saleMovieTotal.setMovieName(saleMovieDO.getMovieName());
                saleMovieTotal.setSort(saleMovieTotal.getSort() + saleMovieDO.getSort());
                saleMovieTotal.setEntranceUV(saleMovieTotal.getEntranceUV() + saleMovieDO.getEntranceUV());
                saleMovieTotal.setOrderUV(saleMovieTotal.getOrderUV() + saleMovieDO.getOrderUV());
                saleMovieTotal.setPayUV(saleMovieTotal.getPayUV() + saleMovieDO.getPayUV());
                saleMovieTotal.setOrderConversion(saleMovieTotal.getOrderConversion() + saleMovieDO.getOrderConversion());
                saleMovieTotal.setPayConversion(saleMovieTotal.getPayConversion() + saleMovieDO.getPayConversion());

            });
            BigDecimal averageSortBg = new BigDecimal(saleMovieTotal.getSort() / (double) saleMoviesList.size());
            Double averageSort = averageSortBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            saleMovieTotal.setAverageSort(averageSort);
            BigDecimal orderConversionBg = new BigDecimal(saleMovieTotal.getOrderConversion() / (double) saleMoviesList.size());
            Double orderConversion = orderConversionBg.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
            saleMovieTotal.setOrderConversion(orderConversion);
            BigDecimal payConversionBg = new BigDecimal(saleMovieTotal.getPayConversion() / (double) saleMoviesList.size());
            Double payConversion = payConversionBg.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
            saleMovieTotal.setPayConversion(payConversion);
            saleMovieTotals.add(saleMovieTotal);
        });
        return saleMovieTotals;
    }
}
