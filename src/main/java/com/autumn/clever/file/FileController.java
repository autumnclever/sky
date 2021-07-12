package com.autumn.clever.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.autumn.clever.file.enums.ExcelType;
import com.autumn.clever.file.eventmodel.XLSXEventModel;
import com.autumn.clever.file.model.FileDTO;
import com.autumn.clever.file.model.JoinActivityDTO;
import com.autumn.clever.file.service.ActivityFileService;
import com.autumn.clever.file.util.ExcelUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
//        SXSSFWorkbook
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
}
