package com.autumn.clever.common;

import au.com.bytecode.opencsv.CSVWriter;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: sky
 * @description: csv文件工具类
 * @author: zhangqiuying
 * @create: 2023-07-05 11:07
 **/
public class CsvUtils {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Date start = getZeroDate(calendar, 2023, 12, 21);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        Date end = calendar.getTime();
        System.out.println(start);
        System.out.println(end);
    }

    public static Date getZeroDate(Calendar calendar, int year, int month, int day) {
        calendar.set(Calendar.YEAR, year);
        // JANUARY = 0
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private static Logger log = LoggerFactory.getLogger(CsvUtils.class);

    public static final String CVS_GBK = "GBK";

    /**
     * 生成 CSV 文件
     *
     * @param outPutPath 文件路径
     * @param fileName   文件名称
     * @param tableHead  csv文件的列表头
     * @param exportData 源数据List
     * @return CSV 文件
     */
    public static File createCSVFile(String outPutPath, String fileName, String[] tableHead,
                                     List<String[]> exportData) {
        try {
            File csvFile = new File(outPutPath + fileName);
            if (!csvFile.exists()) {
                csvFile.createNewFile();
            }
            CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(Files.newOutputStream(csvFile.toPath()), CVS_GBK));
            if (tableHead != null && tableHead.length > 0) {
                csvWriter.writeNext(tableHead);
            }
            if (CollectionUtils.isNotEmpty(exportData)) {
                for (String[] data : exportData) {
                    csvWriter.writeNext(data);
                }
            }
            csvWriter.flush();
            csvWriter.close();
            return csvFile;
        } catch (Exception e) {
            log.error("CsvUtils_createCSVFile CocoException", e);
//            throw new APIException(APIExceptionEnum.CVS_FILE_EXPORT_FAILED);
        }
        return null;
    }
}
