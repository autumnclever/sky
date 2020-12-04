package com.autumn.clever;

//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//
///**
// * @Author: zhangqiuying
// * @Date: 2020/12/3 下午9:38
// */
public class Test {
//    public static void main(String[] args) {
//        Person p1 = new Person(1001, "qiuying");
//        Person p2 = new Person(1002, "wangxiao");
//        Person p3 = new Person(1003, "laoda");
//        List<Person> people = new ArrayList<>();
//        people.add(p1);
//        people.add(p2);
//        people.add(p3);
//
//        if (CollectionUtils.isEmpty(movieVOS) || CollectionUtils.isEmpty(movieRelativePositions)) {
//            return movieVOS;
//        }
//
//        AtomicInteger index = new AtomicInteger(0);
//        Map<Integer, Integer> indexMap = movieVOS.stream()
//                .collect(Collectors.toMap(movieVO -> movieVO.getId(), x -> index.getAndIncrement()));
//
//        movieRelativePositions.stream().forEach(relativePosition -> {
//            Integer type = relativePosition.getChangeType();
//            RelativePositionChangeTypeEnum typeEnum = RelativePositionChangeTypeEnum.getEnumByValue(type);
//            if (typeEnum != null) {
//                switch (typeEnum) {
//                    case DROP:
//                        relativePosition.setChangePosition(relativePosition.getPosition());
//                        break;
//                    case RISE:
//                    case EQUALS:
//                        relativePosition.setChangePosition(-relativePosition.getPosition());
//                        break;
//                }
//            }
//
//            Integer originIndex = indexMap.get(relativePosition.getMovieId());
//            if (originIndex == null) {
//                originIndex = indexMap.size();
//            }
//            relativePosition.setChangePosition(originIndex + relativePosition.getPosition());
//        });
//
//        movieRelativePositions.stream().sorted(Comparator.comparing(MovieRelativePositionModel::getFinalIndex));
//    }
//}
//
//    private List<Person> sortRelativePositionHandle(List<Person> movieVOS, List<MovieRelativePositionModel> movieRelativePositions) {
//        if (CollectionUtils.isEmpty(movieVOS) || CollectionUtils.isEmpty(movieRelativePositions)) {
//            return movieVOS;
//        }
//
//        AtomicInteger index = new AtomicInteger(0);
//        Map<Integer, Integer> indexMap = movieVOS.stream()
//                .collect(Collectors.toMap(movieVO -> movieVO.getId(), x -> index.getAndIncrement()));
//
//        movieRelativePositions.stream().forEach(relativePosition -> {
//            Integer type = relativePosition.getChangeType();
//            RelativePositionChangeTypeEnum typeEnum = RelativePositionChangeTypeEnum.getEnumByValue(type);
//            if (typeEnum != null) {
//                switch (typeEnum) {
//                    case DROP:
//                        relativePosition.setChangePosition(relativePosition.getPosition());
//                        break;
//                    case RISE:
//                    case EQUALS:
//                        relativePosition.setChangePosition(-relativePosition.getPosition());
//                        break;
//                }
//            }
//
//            Integer originIndex = indexMap.get(relativePosition.getMovieId());
//            if (originIndex == null) {
//                originIndex = indexMap.size();
//            }
//            relativePosition.setChangePosition(originIndex + relativePosition.getPosition());
//        });
//
//        movieRelativePositions.stream().sorted(Comparator.comparing(MovieRelativePositionModel::getFinalIndex));
//
//        return movieVOS;
}
//
//class Person {
//    Integer id;
//    String name;
//
//    public Person() {
//    }
//
//    public Person(Integer id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
//
//class MovieRelativePositionModel {
//    /**
//     * 电影id
//     */
//    private Integer movieId;
//    /**
//     * 相对位置变动类型
//     *
//     * @see RelativePositionChangeTypeEnum
//     */
//    private Integer changeType;
//    /**
//     * 相对位置变动值
//     */
//    private Integer position;
//
//    /**
//     * 结合 changeType + position ->
//     * 1.排名上升 2 位，-2;
//     * 2.排名下降 3 位， 3;
//     * 3.排名无变化，0
//     */
//    private Integer changePosition;
//
//    /**
//     * 计算之后得到的 index 位置值
//     */
//    private Integer finalIndex;
//
//    public Integer getMovieId() {
//        return movieId;
//    }
//
//    public void setMovieId(Integer movieId) {
//        this.movieId = movieId;
//    }
//
//    public Integer getChangeType() {
//        return changeType;
//    }
//
//    public void setChangeType(Integer changeType) {
//        this.changeType = changeType;
//    }
//
//    public Integer getPosition() {
//        return position;
//    }
//
//    public void setPosition(Integer position) {
//        this.position = position;
//    }
//
//    public Integer getChangePosition() {
//        return changePosition;
//    }
//
//    public void setChangePosition(Integer changePosition) {
//        this.changePosition = changePosition;
//    }
//
//    public Integer getFinalIndex() {
//        return finalIndex;
//    }
//
//    public void setFinalIndex(Integer finalIndex) {
//        this.finalIndex = finalIndex;
//    }
//}
//
//enum RelativePositionChangeTypeEnum {
//    /**
//     * 相等
//     */
//    EQUALS(0, "相等"),
//    /**
//     * 上升
//     */
//    RISE(1, "上升"),
//    /**
//     * 下降
//     */
//    DROP(2, "下降");
//
//
//    private final int value;
//    private final String desc;
//
//    RelativePositionChangeTypeEnum(int value, String desc) {
//        this.value = value;
//        this.desc = desc;
//    }
//
//    private final static Map<Integer, RelativePositionChangeTypeEnum> VAR_MAP = Maps.newHashMap();
//
//    static {
//        for (RelativePositionChangeTypeEnum e : values()) {
//            VAR_MAP.put(e.getValue(), e);
//        }
//    }
//
//    public int getValue() {
//        return value;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public static RelativePositionChangeTypeEnum getEnumByValue(int value) {
//        return VAR_MAP.get(value);
//    }
//}