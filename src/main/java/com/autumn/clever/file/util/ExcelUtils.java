package com.autumn.clever.file.util;

import com.autumn.clever.file.enums.ExcelType;
import com.autumn.clever.file.eventmodel.EventModel;
import com.autumn.clever.file.read.ReadExcelByEventModel;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/3 11:37 下午
 */
public class ExcelUtils {

    /**
     * Use {@link EventModel} to create Excel object.
     *
     * @param eventModel
     * @param tClass
     * @return
     */
    public static <C> ReadExcelByEventModel<C> readByEventModel(EventModel eventModel, Class<C> tClass) {
        return ReadExcelByEventModel.excel(eventModel, tClass);
    }

    /**
     * 根据文件名与Excel输入流创建ReadExcel对象
     *
     * @param stream    输入流
     * @param tClass    数据对象
     * @param excelType Excel类型
     * @return
     * @throws IOException
     * @throws OpenXML4JException
     */
    public static <C> ReadExcelByEventModel<C> readByEventModel(InputStream stream, Class<C> tClass, ExcelType excelType) throws IOException, OpenXML4JException {
        return ReadExcelByEventModel.excel(stream, tClass, excelType);
    }
}
