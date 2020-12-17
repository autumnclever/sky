package com.autumn.clever.mmdb;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/4 上午10:41
 */
public class MovieRelativePositionModel {
    /**
     * 电影id
     */
    private Integer movieId;
    /**
     * 相对位置变动类型
     *
     * @see RelativePositionChangeTypeEnum
     */
    private Integer changeType;
    /**
     * 相对位置变动值
     */
    private Integer changePosition;

    public MovieRelativePositionModel() {
    }

    public MovieRelativePositionModel(Integer movieId, Integer changeType, Integer changePosition) {
        this.movieId = movieId;
        this.changeType = changeType;
        this.changePosition = changePosition;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Integer getChangePosition() {
        return changePosition;
    }

    public void setChangePosition(Integer changePosition) {
        this.changePosition = changePosition;
    }
}
