package com.autumn.clever.file2.excel;

import com.autumn.clever.file2.bean.CellListOfRow;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/4 3:46 上午
 */
public class XLSXCountEventModel extends EventModel {
    private static Logger log = LoggerFactory.getLogger(XLSXCountEventModel.class);
    private OPCPackage pkg;
    private XSSFReader reader;

    public XLSXCountEventModel(OPCPackage pkg, int startRow, Consumer<CellListOfRow<String>> readConsumer)
            throws IOException, OpenXML4JException {
        super(startRow, readConsumer);
        init(pkg);
    }

    public void init(OPCPackage pkg) throws IOException, OpenXML4JException {
        this.pkg = pkg;
        this.reader = new XSSFReader(pkg);
    }

    public XLSXCountEventModel(InputStream stream, int startRow) throws Exception {
        this(OPCPackage.open(stream), startRow, null);
    }

    protected XMLReader createCountSheetParser() throws Exception {
        XMLReader parser = XMLHelper.newXMLReader();
        ContentHandler handler = createCountSheetXMLHandler();
        parser.setContentHandler(handler);
        return parser;
    }

    protected XSSFSheetXMLHandler createCountSheetXMLHandler() throws Exception {
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.pkg);
        DataFormatter formatter = new DataFormatter();
        XSSFSheetXMLHandler handler = new XSSFSheetXMLHandler(
                reader.getStylesTable(), null, strings, createCountSheetContentsHandler(), formatter, false);
        return handler;
    }

    protected XSSFSheetXMLHandler.SheetContentsHandler createCountSheetContentsHandler() {
        return new XSSFSheetXMLHandler.SheetContentsHandler() {
            private int currentRow = -1;
            private int currentCol = -1;

            @Override
            public void startRow(int rowNum) {
                // 行开始
                currentRow = rowNum;
                currentCol = -1;
            }

            @Override
            public void endRow(int rowNum) {
                // 结束行，如果当前行号 ≥ startRow，则启用消费者功能，否则整行记录直接丢弃，不处理。
                if (rowNum >= startRow) {
                    readConsumer.accept(new CellListOfRow<>(rowCellValues, null));
                }
                // 初始化新的一行
                rowCellValues = new ArrayList<>();
            }

            // 处理单个 cell
            @Override
            public void cell(String cellReference, String formattedValue, XSSFComment comment) {
                System.out.println("cell");
            }
        };
    }

    @Override
    public void process() throws Exception {

    }

    private void processFirstSheet() throws Exception {
        XMLReader parser = createCountSheetParser();
        InputStream sheet = reader.getSheetsData().next();
        InputSource sheetSource = new InputSource(sheet);
        parser.parse(sheetSource);
        sheet.close();
    }
}
