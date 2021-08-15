package com.autumn.clever.file2;

import com.alibaba.fastjson.JSONObject;
import com.autumn.clever.file.model.FileDTO;
import com.autumn.clever.file2.read.ReadCountExcelByEventModel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/7/4 12:38 上午
 */
@RestController
@RequestMapping("/excelFile")
public class ExcelFileController {

    @PostMapping("/uploadExcelFile")
    public Object uploadExcelFile(@RequestBody FileDTO fileDTO) {
        try {
            String fileBase64 = fileDTO.getFileBase64();
            byte[] bytes = Base64.getDecoder().decode(fileBase64);
            InputStream inputStream = new ByteArrayInputStream(bytes);
//            List<JoinActivityDTO> joinActivityDTOS = Lists.newArrayList();
            final int[] count = {0};
            ReadCountExcelByEventModel.excel(inputStream, 1)
                    .window(2, t -> System.out.println(JSONObject.toJSON(t)))
                    .process(cs -> {
                        count[0] += 1;
                        return count[0];
                    }, 1);
            if (count[0] > 3) {
                return false;
            }
            return true;

//            ExcelUtils.readByEventModel(inputStream, JoinActivityDTO.class, ExcelType.XLSX)
//                    .window(2, ts -> System.out.println(JSON.toJSONString(ts)))
//                    .process(cs -> {
//                        JoinActivityDTO joinActivityDTO = new JoinActivityDTO();
//                        joinActivityDTO.setSpuId(cs.get(0));
//                        joinActivityDTO.setSkuId(cs.get(1));
//                        joinActivityDTO.setSalePrice(cs.get(2));
//                        joinActivityDTO.setSaleStock(cs.get(3));
//                        joinActivityDTOS.add(joinActivityDTO);
//                        return joinActivityDTO;
//                    }, 1);
//            System.out.println(JSON.toJSONString(joinActivityDTOS));
//            return joinActivityDTOS;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    class 渣男 extends Exception {

    }

    class 妈宝男 extends Exception {

    }

    class 凤凰男 extends Exception {

    }

    class 林奕 extends Exception {

    }

    static class Man {
    }

    static class RealMan {
    }

    public static void main(String[] args) throws Exception {
        try {
            Man man = new Man();
            找对象(man);
        } catch (Exception man) {
            if (man instanceof 渣男) {
                throw new Exception("去找渣女吧，滚!");
            } else if (man instanceof 妈宝男) {
                throw new Exception("去找你妈妈去吧，滚！");
            } else if (man instanceof 凤凰男) {
                throw new Exception("你是全村的希望，不是我的希望，滚！");
            } else if (!(man instanceof 林奕)) {
                throw new Exception("滚！");
            }
        } finally {
            RealMan 林奕 = new RealMan();
            嫁人(林奕);
        }
    }

    public static void 找对象(Man man) {

    }

    public static void 嫁人(RealMan realMan) {

    }
}
