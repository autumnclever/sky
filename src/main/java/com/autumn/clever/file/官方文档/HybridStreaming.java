package com.autumn.clever.file.官方文档;

import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/3 10:29 下午
 */
public class HybridStreaming {
    private static final String SHEET_TO_STREAM = "large sheet";

    public static void main(String[] args) throws IOException, SAXException {
        try (InputStream sourceBytes = new FileInputStream("workbook.xlsx")) {
            XSSFWorkbook workbook = new XSSFWorkbook(sourceBytes) {
                /**
                 * Avoid DOM parse of large sheet
                 */
                @Override
                public void parseSheet(java.util.Map<String, XSSFSheet> shIdMap, CTSheet ctSheet) {
                    if (!SHEET_TO_STREAM.equals(ctSheet.getName())) {
                        super.parseSheet(shIdMap, ctSheet);
                    }
                }
            };

            // Having avoided a DOM-based parse of the sheet, we can stream it instead.
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(workbook.getPackage());
            new XSSFSheetXMLHandler(workbook.getStylesSource(), strings, createSheetContentsHandler(), false);
            workbook.close();
        }
    }

    private static XSSFSheetXMLHandler.SheetContentsHandler createSheetContentsHandler() {
        return new XSSFSheetXMLHandler.SheetContentsHandler() {

            @Override
            public void startRow(int rowNum) {
            }

            @Override
            public void endRow(int rowNum) {
            }

            @Override
            public void cell(String cellReference, String formattedValue, XSSFComment comment) {
            }
        };
    }
}
