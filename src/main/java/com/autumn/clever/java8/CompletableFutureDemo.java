package com.autumn.clever.java8;

import lombok.Data;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/5 5:22 下午
 */
public class CompletableFutureDemo {
    static ExecutorService executor = Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<XSSFRow> rowList = Lists.newArrayList();

        // 1.excel 的每一行数据，并行处理报名
        List<CompletableFuture<JoinResultDTO>> joinResultFutureList = rowList.parallelStream()
                .map(row -> handler(row))
                .collect(Collectors.toList());

        //
        CompletableFuture<Void> allJoinResultFutureList = CompletableFuture.allOf(
                joinResultFutureList.toArray(new CompletableFuture[joinResultFutureList.size()])
        );

        // 2.并行处理结果统一为一个 CompletableFuture
        CompletableFuture<List<JoinResultDTO>> allJoinResultFuture = allJoinResultFutureList.thenApply(v -> {
            return joinResultFutureList.stream()
                    .map(joinResultFuture -> joinResultFuture.join())
                    .collect(Collectors.toList());
        });

        List<JoinResultDTO> joinResultDTOS = allJoinResultFuture.get();
        ActivityJoinResultDTO activityJoinResultDTO = sumJoinResultFunction.apply(joinResultDTOS);


//        allJoinResultFutureList.join();
//        allJoinResultFutureList.thenApplyAsync()


//        CompletableFuture<ActivityJoinResultDTO> future = CompletableFuture.supplyAsync(
//                new Supplier<ActivityJoinResultDTO>() {
//
//                    @SneakyThrows
//                    @Override
//                    public ActivityJoinResultDTO get() {
//                        try {
//
//                        } catch (Exception e) {
//                            throw new Exception(REPEAT_JOIN.getDesc());
//                        }
//                        return null;
//                    }
//                }).thenApplyAsync(activityJoinResultDTO -> {
//
//            return activityJoinResultDTO;
//        }, executor).
    }

    public static CompletableFuture<JoinResultDTO> handler(XSSFRow row) {
        CompletableFuture<JoinResultDTO> future = CompletableFuture.supplyAsync(
                new Supplier<JoinResultDTO>() {
                    @Override
                    public JoinResultDTO get() {
                        JoinResultDTO joinResultDTO = new JoinResultDTO();
                        Request request = rowRequestFunction.apply(row);
                        addPromotion(request);
                        joinResultDTO.setSuccessCount(1);
                        joinResultDTO.setJoinTotal(1);
                        return joinResultDTO;
                    }
                }, executor)
                .exceptionally(e -> {
                    JoinResultDTO joinResultDTO = new JoinResultDTO();
                    joinResultDTO.setFailCount(1);
                    joinResultDTO.setJoinTotal(1);
                    joinResultDTO.setFailReason(e.getMessage());
                    joinResultDTO.setFailRow(row.getRowNum());
                    return joinResultDTO;
                });
        return future;
    }

//    public static CompletableFuture<ActivityJoinResultDTO> handler(XSSFRow row) {
//        CompletableFuture<ActivityJoinResultDTO> future = CompletableFuture.supplyAsync(
//                new Supplier<ActivityJoinResultDTO>() {
//            try
//
//                    {
//                        JoinResultDTO joinResultDTO = new JoinResultDTO();
//                        Thread.sleep(3000);
//                        return joinResultDTO;
//                    } catch(
//                    InterruptedException e)
//
//                    {
//                        e.printStackTrace();
//                    }
//                }, executor).exceptionally(new Function<Throwable, Void>() {
//            // 捕捉异常,不会导致整个流程中断
//            @Override
//            public Void apply(Throwable throwable) {
//                // 线程[{}]发生了异常, 继续执行其他线程,错误详情[{}]
//                return null;
//            }
//        });
//        return future;
//    }

    public static CompletableFuture<Void> handlerVoid(XSSFRow row) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, executor).exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable throwable) {
                // "线程[{}]发生了异常, 继续执行其他线程,错误详情[{}]"
                return null;
            }
        });
        return future;
    }

    public static Long addPromotion(Request request) {
        return 1L;
    }


    private static Function<XSSFRow, Request> rowRequestFunction = (row) -> {
        Request request = new Request();
        XSSFCell cell = row.getCell(0);
        request.setSpuId(Long.valueOf(getCellValue(cell)));
        return request;
    };

    public static Function<List<JoinResultDTO>, ActivityJoinResultDTO> sumJoinResultFunction = joinResultDTOS -> {
        ActivityJoinResultDTO activityJoinResultDTO = new ActivityJoinResultDTO();
        List<FailRecord> failRecordList = Lists.newArrayList();
        Integer successTotal = Math.toIntExact(joinResultDTOS.stream()
                .filter(joinResultDTO -> joinResultDTO.getSuccessCount().equals(1))
                .count());
//        Integer failTotal =
        for (JoinResultDTO joinResultDTO : joinResultDTOS) {
            if (joinResultDTO.getSuccessCount().equals(1)) {
                Integer successCount = activityJoinResultDTO.getSuccessCount();
                activityJoinResultDTO.setSuccessCount(successCount + 1);
            } else if (joinResultDTO.getFailCount().equals(1)) {
                Integer failCount = activityJoinResultDTO.getFailCount();
                activityJoinResultDTO.setFailCount(failCount);
            }
            Integer joinTotal = activityJoinResultDTO.getJoinTotal();
            activityJoinResultDTO.setJoinTotal(joinResultDTO.getJoinTotal() + joinTotal);
//            FailRecord failRecord = joinResultDTOFailRecordFunction.apply(joinResultDTO);
//            failRecordList.add(failRecord);
        }
        activityJoinResultDTO.setFailRecordList(failRecordList);
        return activityJoinResultDTO;
    };

    private static Function<JoinResultDTO, FailRecord> joinResultDTOFailRecordFunction = joinResultDTO -> {
        FailRecord failRecord = new FailRecord();
        failRecord.setSpuId(joinResultDTO.getSpuId());
        failRecord.setRowNum(joinResultDTO.getFailRow());
        failRecord.setFailReason(joinResultDTO.getFailReason());
        return failRecord;
    };


    public static String getCellValue(XSSFCell cell) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return cell.getStringCellValue();
        }
    }
}

@Data
class Request {
    private Long spuId;
}


@Data
class JoinResultDTO {
    private Long spuId;

    private Integer joinTotal;

    private Integer successCount;

    private Integer failCount;

    private Integer failRow;

    private String failReason;
}


@Data
class ActivityJoinDTO {
    private Long activity;
}

@Data
class ActivityJoinResultDTO {
    private Integer joinTotal;

    private Integer successCount;

    private Integer failCount;

    private List<FailRecord> failRecordList;
}

@Data
class FailRecord {
    /**
     * 失败行数
     */
    private Integer rowNum;

    private Long spuId;

    private String failReason;
}
