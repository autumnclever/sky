package com.autumn.clever.file2.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/4 12:41 上午
 */
@Data
@AllArgsConstructor
public class CellListOfRow<T> {

    private List<T> baseList;

    /**
     * returning the value instead of throwing@{@link ArrayIndexOutOfBoundsException}.
     */
    private T defaultValue;

    public T get(int index) {
        return index >= baseList.size() ? defaultValue : baseList.get(index);
    }

    public int size() {
        return baseList.size();
    }
}
