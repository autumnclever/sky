package com.autumn.clever.file2.read;


import com.autumn.clever.file2.bean.CellListOfRow;
import com.autumn.clever.file2.excel.EventModel;
import com.autumn.clever.file2.excel.XLSXEventModel;

import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/4 2:58 上午
 */
public class ReadExcelByEventModel<T> extends ReadExcel<CellListOfRow<String>, T> {

    private EventModel eventModel;

    public ReadExcelByEventModel(EventModel eventModel) {
        super();
        this.eventModel = eventModel;
    }

    /**
     * Use {@link EventModel} to create Excel object.
     *
     * @param stream
     * @param startRow
     * @param <C>
     * @return
     * @throws Exception
     */
    public static <C> ReadExcelByEventModel<C> excel(InputStream stream, int startRow) throws Exception {
        EventModel eventModel = new XLSXEventModel(stream, startRow);
        return new ReadExcelByEventModel<>(eventModel);
    }

    @Override
    public ReadExcelByEventModel<T> process(Function<CellListOfRow<String>, T> readFunction, int startNum) throws Exception {
        super.process(readFunction, startNum);
        //每读完一行记录（one row of records），调用该函数
        Consumer<CellListOfRow<String>> readConsumer = cells -> {
            list.add(readFunction.apply(cells));
            //调用监听函数，执行自定义逻辑
            invokeListener();
        };
        eventModel.startRow(startNum)
                .readConsumer(readConsumer)
                .process();
        clearList();
        return this;
    }
}
