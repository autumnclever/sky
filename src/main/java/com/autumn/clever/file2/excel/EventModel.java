package com.autumn.clever.file2.excel;


import com.autumn.clever.file2.bean.CellListOfRow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/4 12:39 上午
 */
public abstract class EventModel {
    /**
     * 起始行号
     */
    protected int startRow;

    /**
     * 每次读取一行数据，都会调用函数接口执行自定义逻辑。
     */
    protected Consumer<CellListOfRow<String>> readConsumer;

    /**
     * 存储出现在一行单元格中的记录值
     */
    protected List<String> rowCellValues;

    public EventModel(int startRow, Consumer<CellListOfRow<String>> readConsumer) {
        this.startRow = startRow;
        this.readConsumer = readConsumer;
        this.rowCellValues = new ArrayList<>();
    }

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

    public abstract void process() throws Exception;
}
