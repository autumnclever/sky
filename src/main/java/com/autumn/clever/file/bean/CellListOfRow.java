package com.autumn.clever.file.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * A special list wrapper for handling @{@link ArrayIndexOutOfBoundsException} due to Missing Cells at the end of row.
 *
 * @author zhangqiuying
 * @description
 * @date 2021/7/3 12:43 下午
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
