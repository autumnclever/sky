package com.autumn.clever.mmdb;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/4 上午10:42
 */
public class MovieVO {
    Integer id;
    String name;
    Integer index;

    public MovieVO() {
    }

    public MovieVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
