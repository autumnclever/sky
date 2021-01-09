package com.autumn.clever.excel;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/5 上午10:57
 */
@Service
public class FileService {
    public List<SaleMovieDO> importSaleMovie(MultipartFile file, String date) {
        List<SaleMovieDO> saleMovieDOList = Lists.newArrayList();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

            XSSFSheet sheet = workbook.getSheetAt(0);
            // 获取到 Excel 文件中的所有行数
            int rows = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rows; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row != null) {
                    SaleMovieDO saleMovieDO = buildSaleMovieByCell(row);
                    if (date.equals(saleMovieDO.getDate())) {
                        saleMovieDOList.add(saleMovieDO);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return saleMovieDOList;
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

    private SaleMovieDO buildSaleMovieByCell(XSSFRow row) {
        SaleMovieDO saleMovieDO = new SaleMovieDO();
        Object movieIdCell = getCellValue(row.getCell(4));
        String movieIdStr = movieIdCell.toString();
        int movieId = Integer.valueOf(movieIdStr);
        saleMovieDO.setMovieId(movieIdCell == null ? 0 : movieId);
        Object nameCell = getCellValue(row.getCell(5));
        saleMovieDO.setMovieName(nameCell == null ? "" : String.valueOf(nameCell));
        Object sortCell = getCellValue(row.getCell(6));
        saleMovieDO.setSort(sortCell == null ? 11 : Integer.valueOf(sortCell.toString()));
        Object entranceCell = getCellValue(row.getCell(7));
        saleMovieDO.setEntranceUV(entranceCell == null ? 0 : Integer.valueOf(entranceCell.toString()));
        Object orderCell = getCellValue(row.getCell(8));
        saleMovieDO.setOrderUV(orderCell == null ? 0 : Integer.valueOf(orderCell.toString()));
        Object payCell = getCellValue(row.getCell(9));
        saleMovieDO.setPayUV(payCell == null ? 0 : Integer.valueOf(payCell.toString()));
        Object orderConversionCell = getCellValue(row.getCell(10));
        saleMovieDO.setOrderConversion(orderConversionCell == null ? 0.0d : (double) orderConversionCell);
        Object payConversionCell = getCellValue(row.getCell(11));
        saleMovieDO.setPayConversion(payConversionCell == null ? 0.0d : (double) payConversionCell);
        Object dateCell = getCellValue(row.getCell(0));
        saleMovieDO.setDate(dateCell == null ? "" : String.valueOf(dateCell));
        return saleMovieDO;
    }

    public Object getCellValue(XSSFCell cell) {
        if (cell == null) {
            return null;
        }
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                return cell.getStringCellValue();
            default:
                return null;
        }
    }
}
