package com.autumn.clever.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.autumn.clever.file.enums.ExcelType;
import com.autumn.clever.file.eventmodel.XLSXEventModel;
import com.autumn.clever.file.model.FileDTO;
import com.autumn.clever.file.model.JoinActivityDTO;
import com.autumn.clever.file.model.JoinActivityDTO2;
import com.autumn.clever.file.service.ActivityFileService;
import com.autumn.clever.file.util.ExcelUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/2 11:21 上午
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private ActivityFileService activityFileService;

    @PostMapping("/uploadFile")
    public Object uploadFile(@RequestBody FileDTO fileDTO) {
        try {
//            byte[] xlsBytes = Base64.decodeBase64(fileString);
//            InputStream inputStream = new ByteArrayInputStream(xlsBytes);
            String filePath = fileDTO.getFilePath();
            String fileBase64 = fileDTO.getFileBase64();
            byte[] bytes = fileBase64.getBytes(StandardCharsets.UTF_8);
//            String fileEncodeString = Base64.getEncoder().encodeToString(bytes);
//            FileOutputStream outputStream = new FileOutputStream(filePath);
//            OutputStream os = Base64.getEncoder().wrap(outputStream);
//            os.write();
//            Path path = Files.write(Paths.get(filePath), Base64.getEncoder().encode(fileBase64), StandardOpenOption.CREATE);
//            Path path = Files.write(Paths.get(filePath), Base64.getDecoder().decode(fileBase64), StandardOpenOption.CREATE);
            byte[] bytes1 = Base64.getDecoder().decode(bytes);
//            Path path = Files.write(Paths.get(filePath), Base64.getDecoder().decode(bytes), StandardOpenOption.CREATE);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            Path path = Files.write(Paths.get(filePath), bytes1, StandardOpenOption.CREATE);
//            Path path = Paths.get(fileString);
            Stream<String> fileStream = Files.lines(path);
            Long count = fileStream.count();
            return count;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @PostMapping("/uploadFile2")
    public Object uploadFile2(@RequestBody FileDTO fileDTO) {
        try {
//            byte[] xlsBytes = Base64.decodeBase64(fileString);
//            InputStream inputStream = new ByteArrayInputStream(xlsBytes);
            String filePath = fileDTO.getFilePath();
            String fileBase64 = fileDTO.getFileBase64();
            byte[] bytes = Base64.getDecoder().decode(fileBase64);
            InputStream resourceAsStream = new ByteArrayInputStream(bytes);

            List<JoinActivityDTO> joinActivityDTOS = Lists.newArrayList();
            XLSXEventModel xlsx = new XLSXEventModel(resourceAsStream, 0, cs -> {
//                JoinActivityDTO joinActivityDTO = new JoinActivityDTO();
//                joinActivityDTO.setSpuId(cs.get(0));
//                joinActivityDTO.setSkuId(cs.get(1));
//                joinActivityDTO.setSalePrice(cs.get(2));
//                joinActivityDTO.setSaleStock(cs.get(3));
//                joinActivityDTOS.add(joinActivityDTO);
            });
            xlsx.process();
            System.out.println(JSON.toJSONString(joinActivityDTOS));
            return joinActivityDTOS;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @PostMapping("/uploadFile3")
    public Object uploadFile3(@RequestBody FileDTO fileDTO) {
        try {
            String fileBase64 = fileDTO.getFileBase64();
            byte[] bytes = Base64.getDecoder().decode(fileBase64);
            InputStream resourceAsStream = new ByteArrayInputStream(bytes);
            List<JoinActivityDTO> joinActivityDTOS = Lists.newArrayList();
            ExcelUtils.readByEventModel(resourceAsStream, JoinActivityDTO.class, ExcelType.XLSX)
                    .window(2, ts -> System.out.println(JSON.toJSONString(ts)))
                    .process(cs -> {
                        JoinActivityDTO joinActivityDTO = new JoinActivityDTO();
//                        joinActivityDTO.setSpuId(cs.get(0));
//                        joinActivityDTO.setSkuId(cs.get(1));
//                        joinActivityDTO.setSalePrice(cs.get(2));
//                        joinActivityDTO.setSaleStock(cs.get(3));
//                        joinActivityDTOS.add(joinActivityDTO);
                        return joinActivityDTO;
                    }, 1);
            System.out.println(JSON.toJSONString(joinActivityDTOS));
            return joinActivityDTOS;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static void main(String[] args) {
        String salePriceStr = "0.01";
        Double salePriceDou = Double.valueOf(salePriceStr) * 100;
        Long salePrice = salePriceDou.longValue();
        System.out.println(salePrice);
    }

    private static final String[] chTitles2 = {"spuId", "skuId", "salePrice", "saleStock"};

    @RequestMapping("/uploadJoinActivityFile")
    public Object uploadJoinActivityFile(@RequestParam(name = "joinActivityFile", required = false) MultipartFile joinActivityFile,
                                         HttpServletResponse response) {
        System.out.println("startTime:" + System.currentTimeMillis());
        List<JoinActivityDTO> joinActivityDTOS = activityFileService.importJoinActivityFile(joinActivityFile);
        System.out.println("endTime:" + System.currentTimeMillis());

        System.out.println("startGroupBy:" + System.currentTimeMillis());
        Map<String, List<JoinActivityDTO>> joinActivityDTOMap = joinActivityDTOS.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(JoinActivityDTO::getSpuId));
        System.out.println("endGroupBy:" + System.currentTimeMillis());
        List<JSONObject> jsonObjectList = Lists.newArrayList();
        joinActivityDTOS.stream().forEach(joinActivityDTO -> {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(joinActivityDTO);
            jsonObjectList.add(jsonObject);
        });
        System.out.println("startCreateSXSSFWorkbook:" + System.currentTimeMillis());
        XSSFWorkbook workbook = activityFileService.getXSSFWorkbook("joinActivityFile文件导出测试", chTitles2, chTitles2, jsonObjectList);
        System.out.println("endCreateSXSSFWorkbook:" + System.currentTimeMillis());
        System.out.println("startExportExcel:" + System.currentTimeMillis());
        exportExcel(response, workbook, "热映列表");
        System.out.println("endExportExcel:" + System.currentTimeMillis());
        return true;
    }

    @RequestMapping("/uploadJoinActivityFile2")
    public Object uploadJoinActivityFile2(@RequestParam(name = "joinActivityFile", required = false) MultipartFile joinActivityFile,
                                          HttpServletResponse response) {
        System.out.println("startTime:" + System.currentTimeMillis());
        List<JoinActivityDTO> joinActivityDTOS = activityFileService.importJoinActivityFile(joinActivityFile);
        System.out.println("endTime:" + System.currentTimeMillis());

        System.out.println("startGroupBy:" + System.currentTimeMillis());
        Map<String, List<JoinActivityDTO>> joinActivityDTOMap = joinActivityDTOS.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(JoinActivityDTO::getSpuId));
        System.out.println("endGroupBy:" + System.currentTimeMillis());
        List<JsonNode> jsonObjectList = Lists.newArrayList();
        joinActivityDTOS.stream().forEach(joinActivityDTO -> {
            JsonNode jsonObject = (JsonNode) JSONObject.toJSON(joinActivityDTO);
            jsonObjectList.add(jsonObject);
        });
        System.out.println("startCreateSXSSFWorkbook:" + System.currentTimeMillis());
//        XSSFWorkbook workbook = activityFileService.getXSSFWorkbook2("joinActivityFile文件导出测试", chTitles2, chTitles2, jsonObjectList);
        System.out.println("endCreateSXSSFWorkbook:" + System.currentTimeMillis());
        System.out.println("startExportExcel:" + System.currentTimeMillis());
//        exportExcel(response, workbook, "热映列表");
        System.out.println("endExportExcel:" + System.currentTimeMillis());
        return true;
    }

    @RequestMapping("/uploadJoinActivityFile3")
    public Object uploadJoinActivityFile3(@RequestParam(name = "joinActivityFile", required = false) MultipartFile joinActivityFile,
                                          HttpServletResponse response) {
        List<JoinActivityDTO2> joinActivityDTOS = activityFileService.importJoinActivityFile2(joinActivityFile);
        return joinActivityDTOS;
    }

    /**
     * 活动报名文件标题
     */
    public static final String[] ACTIVITY_SIGN_UP_FILE_HEADS =
            {"商品id（SPUID）", "规格id（SKUID）", "SKU活动价格（元）", "SKU活动库存"};

    public static final String[] ROW_ONE = {"导入示例"};
    public static final String[] ROW_TWO = {"1234567", "1111111111", "10", "5"};
    public static final String[] ROW_THREE = {"1234567", "1111111112", "15", "10"};
    public static final String[] ROW_FOUR = {"2345678", "1111111113", "20", "5"};
    public static final String[] ROW_FIVE = {"2345678", "1111111114", "25", "5"};
    public static final String[] ROW_SIX = {"说明"};
    public static final String[] ROW_SEVEN = {"1、导入SKU活动价格必须大于0且小于SKU当前售价；"};
    public static final String[] ROW_EIGHT = {"2、导入SKU活动库存必须大于0且小于SKU当前库存；"};
    public static final String[] ROW_NINE = {"2、导入SKU活动库存必须大于0且小于SKU当前库存；"};
    public static final String[] ROW_TEN = {"3、导入商品支付方式必须为【在线支付】;"};
    public static final String[] ROW_ELEVEN = {"4、参加其他活动商品不支持报名；"};
    public static final String[] ROW_TWELVE = {"5、某个规格id导入失败后，对应商品id的所有规格id需要重新进行导入；"};
    public static final String[] ROW_THIRTEEN = {"6、导入数量最多一次性支持5W，一行数据为一条数量；"};
    public static final String[] ROW_FOURTEEN = {"7、导入时请删除示例，表头必须与模板保持一致；"};
    /**
     * 活动报名文件模板
     */
    public static final List<String[]> ACTIVITY_SIGN_UP_TEMPLATE = Arrays.asList(
            ROW_ONE, ROW_TWO, ROW_THREE, ROW_FOUR, ROW_FIVE,
            ROW_SIX, ROW_SEVEN, ROW_EIGHT, ROW_NINE, ROW_TEN,
            ROW_ELEVEN, ROW_TWELVE, ROW_THIRTEEN, ROW_FOURTEEN);

    @RequestMapping("/generateCSVFile")
    public Object generateCSVFile(HttpServletResponse response) {
        String filePath = System.getProperty("java.io.tmpdir") + "/";
        String fileName = "下载模板" + ".csv";
//        File file = CsvUtils.createCSVFile(filePath, fileName, ACTIVITY_SIGN_UP_FILE_HEADS, ACTIVITY_SIGN_UP_TEMPLATE);
        File csvFile = activityFileService.createCSVFile(filePath, fileName, ACTIVITY_SIGN_UP_FILE_HEADS, ACTIVITY_SIGN_UP_TEMPLATE);
        exportCSVExcel(response, csvFile, fileName);
        return true;
    }

    @RequestMapping("/uploadJoinActivityFile4")
    public Object uploadJoinActivityFile4(@RequestParam(name = "joinActivityFile", required = false) MultipartFile joinActivityFile,
                                          @RequestParam(name = "charsetName", required = false, defaultValue = "GBK") String charsetName,
                                          HttpServletResponse response) {
        try {
            byte[] bytes = joinActivityFile.getBytes();
            String codeType = getCodeType(bytes);
            InputStream inputStream = joinActivityFile.getInputStream();
            byte[] bytes2 = inputStreamToByteArr(inputStream);
            String codeType2 = getCodeType(bytes2);
            List<JoinActivityDTO2> joinActivityDTOS = activityFileService.importJoinActivityFile3(joinActivityFile, codeType2);
            return joinActivityDTOS;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void exportExcel(HttpServletResponse response, XSSFWorkbook workbook, String fileName) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");

            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (Exception e) {

        }
    }


    public void exportCSVExcel(HttpServletResponse response, File csvFile, String fileName) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");

            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".csv");
            OutputStream out = response.getOutputStream();
            InputStream in = new FileInputStream(csvFile);
            int len = 0;
            //5.创建数据缓冲区
            byte[] buffer = new byte[1024];
            //6.通过response对象获取OutputStream流
            //7.将FileInputStream流写入到buffer缓冲区
            while ((len = in.read(buffer)) > 0) {
                //8.使用OutputStream将缓冲区的数据输出到客户端浏览器
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {

        }
    }

    /**
     * InputStream -> byte[]
     * (谨慎使用：仅读取前 100 个字节)
     *
     * @param inputStream 文件二进制字节流
     * @return byte 数组
     * @throws IOException
     */
    public static byte[] inputStreamToByteArr(InputStream inputStream)
            throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        if ((rc = inputStream.read(buff, 0, 100)) > 0) {
            byteArrayOutputStream.write(buff, 0, rc);
        }
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }

    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        if ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
//        while ((rc = inStream.read(buff, 0, 100)) > 0) {
//            swapStream.write(buff, 0, rc);
//        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    /**
     * 获取二进制文件字节流中内容的编码格式
     * utf-8:对应二进制编码格式头为-17 -69 -65 十进制为：EF BB BF
     * unicode:对应二进制编码格式头为-1 -2  十进制为：FF FE
     * gbk格式没有自己编码头，所以无法比较.且gbk2312与gbk是包含关系
     *
     * @param bytes 二进制字节流字节数组
     * @return 文件编码
     */
    public static String getCodeType(byte[] bytes) {
        if (bytes[0] == -17 && bytes[1] == -69 && bytes[2] == -65) {
            return "UTF-8";
        } else if (bytes[0] == -1 && bytes[1] == -2) {
            return "UNICODE";
        } else {
            return "GBK";
        }
    }
}
