package com.autumn.clever.file.service;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.alibaba.fastjson.JSONObject;
import com.autumn.clever.excel.SaleMovieDO;
import com.autumn.clever.file.model.JoinActivityDTO;
import com.autumn.clever.file.model.JoinActivityDTO2;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/6 7:11 下午
 */
@Service
public class ActivityFileService {

    public List<JoinActivityDTO> importJoinActivityFile(MultipartFile file) {
        List<JoinActivityDTO> joinActivityDTOList = Lists.newArrayList();
        try {
            System.out.println("开始 importJoinActivityFile");
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            System.out.println("结束 importJoinActivityFile");

            XSSFSheet sheet = workbook.getSheetAt(0);
            // 获取到 Excel 文件中的所有行数
            int rows = sheet.getPhysicalNumberOfRows();
            System.out.println("JoinActivityFile 总行数：" + rows);

            for (int i = 1; i < rows; i++) {
                System.out.print("正在解析第" + i + "行, ");
                XSSFRow row = sheet.getRow(i);
                if (row != null) {
                    JoinActivityDTO joinActivityDTO = rowJoinActivityDTOFunction.apply(row);
                    if (joinActivityDTO != null) {
                        joinActivityDTOList.add(joinActivityDTO);
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        return joinActivityDTOList;
    }

    public List<JoinActivityDTO2> importJoinActivityFile2(MultipartFile file) {
        List<JoinActivityDTO2> joinActivityDTOList = Lists.newArrayList();
        try {
            System.out.println("开始 importJoinActivityFile");
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            System.out.println("结束 importJoinActivityFile");

            XSSFSheet sheet = workbook.getSheetAt(0);
            // 获取到 Excel 文件中的所有行数
            int rows = sheet.getPhysicalNumberOfRows();
            System.out.println("JoinActivityFile 总行数：" + rows);

            for (int i = 1; i < rows; i++) {
                System.out.print("正在解析第" + i + "行, ");
                XSSFRow row = sheet.getRow(i);
                if (row != null) {
                    JoinActivityDTO2 joinActivityDTO = rowJoinActivityDTOFunction2.apply(row);
                    if (joinActivityDTO != null) {
                        joinActivityDTOList.add(joinActivityDTO);
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        return joinActivityDTOList;
    }

    public List<JoinActivityDTO2> importJoinActivityFile3(MultipartFile file, String charsetName) {
        List<JoinActivityDTO2> joinActivityDTOList = Lists.newArrayList();
        try {
            System.out.println("开始 importJoinActivityFile");
            Stopwatch stopwatch = Stopwatch.createStarted();
            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream(), charsetName));
            long csvReadCostTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("csvReadCostTime" + csvReadCostTime);
            stopwatch.reset().start();
            List<String[]> csvRows = csvReader.readAll();
            long csvReadAllCostTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("csvReadAllCostTime" + csvReadAllCostTime);
            int totalRow = csvRows.size();
            System.out.println("csv 文件总行数是：" + totalRow);
            stopwatch.reset().start();
            String[] heads = csvRows.get(0);
            Arrays.stream(heads).forEach(head -> System.out.println("标题：" + head));
            csvRows.stream().forEach(csvRow -> {
                Arrays.stream(csvRow).forEach(str -> System.out.print(""));
            });
            long csvReadRowCostTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("csvReadRowCostTime" + csvReadRowCostTime);
            System.out.println("结束 importJoinActivityFile");
        } catch (Exception e) {
            return null;
        }
        return joinActivityDTOList;
    }

    /**
     * 生成 CSV 文件
     *
     * @param outPutPath 文件路径
     * @param fileName   文件名称
     * @param tableHead  csv文件的列表头
     * @param exportData 源数据List
     * @return CSV 文件
     */
    public File createCSVFile(String outPutPath, String fileName, String[] tableHead,
                              List<String[]> exportData) {
        try {
            File csvFile = new File(outPutPath + fileName);
            if (!csvFile.exists()) {
                csvFile.createNewFile();
            }
            CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(
                    new FileOutputStream(csvFile), "GBK"));
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
            return null;
        }
    }

    public static String parseRowErrorMessage = "第%d行第%d列数据解析失败";

    public Function<XSSFRow, JoinActivityDTO> rowJoinActivityDTOFunction = row -> {
        JoinActivityDTO joinActivityDTO = new JoinActivityDTO();
        String cellValue = "";
        try {
            cellValue = getCellValue(row.getCell(0));
            if (cellValue == null) {
                String errorMessage = String.format(parseRowErrorMessage, row.getRowNum() + 1, 1);
                throw new Exception(errorMessage);
            } else {
//                joinActivityDTO.setSpuId(Long.valueOf(cellValue));
                joinActivityDTO.setSpuId(cellValue);
            }
            cellValue = getCellValue(row.getCell(1));
            if (cellValue == null) {
                String errorMessage = String.format(parseRowErrorMessage, row.getRowNum() + 1, 2);
                throw new Exception(errorMessage);
            } else {
//                joinActivityDTO.setSkuId(Long.valueOf(cellValue));
                joinActivityDTO.setSkuId(cellValue);
            }
            cellValue = getCellValue(row.getCell(2));
            if (cellValue == null) {
                String errorMessage = String.format(parseRowErrorMessage, row.getRowNum() + 1, 3);
                throw new Exception(errorMessage);
            } else {
//                joinActivityDTO.setSalePrice(100 * Long.valueOf(cellValue));
                joinActivityDTO.setSalePrice(cellValue);
            }
            cellValue = getCellValue(row.getCell(3));
            if (cellValue == null) {
                String errorMessage = String.format(parseRowErrorMessage, row.getRowNum() + 1, 4);
                throw new Exception(errorMessage);
            } else {
//                joinActivityDTO.setSaleStock(Integer.valueOf(cellValue));
                joinActivityDTO.setSaleStock(cellValue);
            }
        } catch (Exception e) {
            return null;
        }
        return joinActivityDTO;
    };

    public Function<XSSFRow, JoinActivityDTO2> rowJoinActivityDTOFunction2 = row -> {
        JoinActivityDTO2 joinActivityDTO = new JoinActivityDTO2();
        String cellValue = null;
        try {
            cellValue = getCellValue2(row.getCell(0));
            if (cellValue == null) {
                String errorMessage = String.format(parseRowErrorMessage, row.getRowNum() + 1, 1);
                throw new Exception(errorMessage);
            } else {
                Long spuId = Long.valueOf(cellValue);
                joinActivityDTO.setSpuId(spuId);
            }
            cellValue = getCellValue2(row.getCell(1));
            if (cellValue == null) {
                String errorMessage = String.format(parseRowErrorMessage, row.getRowNum() + 1, 2);
                throw new Exception(errorMessage);
            } else {
                Long skuId = Long.valueOf(cellValue);
                joinActivityDTO.setSkuId(skuId);
            }
            cellValue = getCellValue2(row.getCell(2));
            if (cellValue == null) {
                String errorMessage = String.format(parseRowErrorMessage, row.getRowNum() + 1, 3);
                throw new Exception(errorMessage);
            } else {
                Long salePrice = Long.valueOf(cellValue);
                joinActivityDTO.setSalePrice(salePrice);
            }
            cellValue = getCellValue2(row.getCell(3));
            if (cellValue == null) {
                String errorMessage = String.format(parseRowErrorMessage, row.getRowNum() + 1, 4);
                throw new Exception(errorMessage);
            } else {
                Integer saleStock = Integer.valueOf(cellValue);
                joinActivityDTO.setSaleStock(saleStock);
            }
        } catch (Exception e) {
            return null;
        }
        return joinActivityDTO;
    };

    public String getCellValue(XSSFCell cell) {
        if (cell == null) {
            return null;
        }
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }

    public String getCellValue2(XSSFCell cell) {
        if (cell == null) {
            return null;
        }
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case NUMERIC:
                DecimalFormat decimalFormat = new DecimalFormat("0");
                return decimalFormat.format(cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }

    public XSSFWorkbook getXSSFWorkbook(String sheetName, String[] titles, String[] enTitles, List<JSONObject> jsonObjectList) {
        try {
            // 创建工作薄
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 创建表单
            XSSFSheet sheet = workbook.createSheet(sheetName);
            // 创建第一行 - 标题，index从0开始
            XSSFRow row = sheet.createRow(0);
            // 声明列对象
            XSSFCell cell;

            for (int i = 0; i < titles.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(titles[i]);
            }

            for (int i = 0; i < jsonObjectList.size(); i++) {
                row = sheet.createRow(i + 1);
                JSONObject dataJson = jsonObjectList.get(i);
                for (int j = 0; j < enTitles.length; j++) {
                    Object dataValue = dataJson.get(enTitles[j]);
                    if (dataValue != null) {
                        row.createCell(j).setCellValue(String.valueOf(dataValue));
                    }
                }
            }
            return workbook;
        } catch (Exception e) {
//            logger.error("ExcelServiceGetXSSFWorkbook failed");
        }
        return null;
    }

    public static XSSFWorkbook getXSSFWorkbook2(String sheetName, String[] titles,
                                                String[] enTitles, List<JsonNode> jsonObjectList) {
        try {
            // 创建工作薄
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 创建表单
            XSSFSheet sheet = workbook.createSheet(sheetName);
            // 创建第一行 - 标题，index从0开始
            XSSFRow row = sheet.createRow(0);
            // 声明列对象
            XSSFCell cell;

            for (int i = 0; i < titles.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(titles[i]);
            }

            for (int i = 0; i < jsonObjectList.size(); i++) {
                row = sheet.createRow(i + 1);
                JsonNode dataJson = jsonObjectList.get(i);
//                JSONObject dataJson = jsonObjectList.get(i);
                for (int j = 0; j < enTitles.length; j++) {
                    Object dataValue = dataJson.get(enTitles[j]);
                    if (dataValue != null) {
                        row.createCell(j).setCellValue(String.valueOf(dataValue));
                    }
                }
            }
            return workbook;
        } catch (Exception e) {
//            logger.error("ExcelServiceGetXSSFWorkbook failed");
        }
        return null;
    }

    public SXSSFWorkbook getSXSSFWorkbook(String sheetName, String[] titles, String[] enTitles, List<JSONObject> jsonObjectList) {
        try {
            // 创建工作薄
            SXSSFWorkbook workbook = new SXSSFWorkbook();
            // 创建表单
            SXSSFSheet sheet = workbook.createSheet(sheetName);
            // 创建第一行 - 标题，index从0开始
            SXSSFRow row = sheet.createRow(0);
            // 声明列对象
            SXSSFCell cell;

            for (int i = 0; i < titles.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(titles[i]);
            }

            for (int i = 0; i < jsonObjectList.size(); i++) {
                row = sheet.createRow(i + 1);
                JSONObject dataJson = jsonObjectList.get(i);
                for (int j = 0; j < enTitles.length; j++) {
                    Object dataValue = dataJson.get(enTitles[j]);
                    if (dataValue != null) {
                        row.createCell(j).setCellValue(String.valueOf(dataValue));
                    }
                }
            }
            return workbook;
        } catch (Exception e) {
//            logger.error("ExcelServiceGetXSSFWorkbook failed");
        }
        return null;
    }

    public static void main(String[] args) {
        String name = "sdfos.csv";
        String[] namaArr1 = name.split("\\.");
        System.out.println(namaArr1[0]);
        System.out.println(namaArr1[1]);
        String[] namaArr = name.split(".");
        System.out.println(namaArr[0]);
        System.out.println(namaArr[1]);
    }
}
