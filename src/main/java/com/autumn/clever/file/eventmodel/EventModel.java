package com.autumn.clever.file.eventmodel;

import com.autumn.clever.file.bean.CellListOfRow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/3 12:49 下午
 */
public abstract class EventModel {

    /**
     * the starting line number as a condition on enabling consumer function, 0-based
     */
    protected int startRow;

    /**
     * Each time a row of data is read, the function interface is called to execute the custom logic.
     * 每读完一行数据，该方法执行一次
     */
    protected Consumer<CellListOfRow<String>> readConsumer;

    /**
     * Store record values that appear in a row of cells
     */
    protected List<String> rowCellValues;

    public EventModel(int startRow, Consumer<CellListOfRow<String>> readConsumer) {
        this.startRow = startRow;
        this.readConsumer = readConsumer;
        this.rowCellValues = new ArrayList<>();
    }

    /**
     * Initiates the processing of the Excel file to Bean.
     *
     * @throws Exception
     */
    public abstract void process() throws Exception;

    /**
     * 启用消费函数的起始行
     *
     * @param startRow
     * @return
     */
    public EventModel startRow(int startRow) {
        this.startRow = startRow;
        return this;
    }

    /**
     * 设置消费函数。每读完一行记录（one row of records），调用该函数。
     *
     * @param readConsumer
     * @return
     */
    public EventModel readConsumer(Consumer<CellListOfRow<String>> readConsumer) {
        this.readConsumer = readConsumer;
        return this;
    }
}
