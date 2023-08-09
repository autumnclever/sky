package com.autumn.clever.file3;

import au.com.bytecode.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @program: sky
 * @description: csv文件导出功能测试
 * @author: zhangqiuying
 * @create: 2023-07-10 19:55
 **/
@Slf4j
@RestController
@RequestMapping("/csv/file")
public class CsvFileController {

    public static final String CVS_UTF_8 = "UTF-8";

    public static final String CVS_GBK = "GBK";

    /**
     * 系统 java.io.tmpdir 属性key
     */
    public static final String SYSTEM_TMPDIR_PROPERTY_KEY = "java.io.tmpdir";

    public static void main(String[] args) {
//        Date date = new Date();
//        System.out.println(date);
//        int i = 1 & 1;
        CouponInfo couponInfo1 = new CouponInfo();
        CouponInfo couponInfo2 = new CouponInfo("1");
        List<CouponInfo> couponInfoList = Lists.newArrayList();
        couponInfoList.add(couponInfo1);
        couponInfoList.add(couponInfo2);

        Map<String, List<CouponInfo>> discountCouponMap = null;
        discountCouponMap = couponInfoList.stream()
                .filter(couponInfo -> Objects.nonNull(couponInfo.getPlatformPromotionId()))
                .collect(Collectors.groupingBy(CouponInfo::getPlatformPromotionId));
        for (String key : discountCouponMap.keySet()) {
            System.out.println(key);
        }
    }


    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public Object exportPaySettleSplitData(
            HttpServletResponse response) {
        try {
            String resultFilePath = System.getProperty(SYSTEM_TMPDIR_PROPERTY_KEY) + "/";
            String fileName = String.valueOf(new Date());
            List<String[]> totalDataList = Lists.newArrayList();
            File file = createCSVFile(resultFilePath, fileName, TABLE_HEADER, totalDataList);
            exportCSVExcel(response, file, fileName);
            FileUtils.deleteFile(file);
        } catch (Exception e) {
            log.error("OrderPaySettleController_exportPaySettleSplitData", e);
        }
        return null;
    }

    public void exportCSVExcel(HttpServletResponse response, File csvFile, String fileName) {
        if (csvFile == null) {
            return;
        }
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
            log.error("OrderPaySettleController_exportCSVExcel", e);
        }
    }

    /**
     * 生成 CSV 文件
     *
     * @param outPutPath 文件路径
     * @param fileName   文件名称
     * @param tableHead  csv文件的列表头
     * @param exportData 源数据List
     * @return CSV 文件
     */
    public static File createCSVFile(String outPutPath, String fileName, String[] tableHead,
                                     List<String[]> exportData) {
        try {
            File csvFile = new File(outPutPath + fileName);
            if (!csvFile.exists()) {
                csvFile.createNewFile();
            }
            CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csvFile), CVS_GBK));
            if (tableHead != null && tableHead.length > 0) {
                csvWriter.writeNext(tableHead);
            }
            if (CollectionUtils.isNotEmpty(exportData)) {
                for (String[] data : exportData) {
                    csvWriter.writeNext(data);
                }
            }
            csvWriter.flush();
            csvWriter.close();
            return csvFile;
        } catch (Exception e) {
            log.error("APPLEB_CsvUtils_createCSVFile_Exception", e);
//            throw new APIException(APIExceptionEnum.CVS_FILE_EXPORT_FAILED);
        }
        return null;
    }

    /**
     * 支付单导出数据 excel 表头
     */
    public static final String[] TABLE_HEADER = {
            "交易系统APPID", "收银台第三方id", "下单日期", "支付日期", "业务侧订单id",
            "业务线", "业务线公司主体", "子订单号", "二级类目", "抽佣比例",
            "商家id", "店铺名称", "商家名称", "商品id", "商品名称",
            "购买件数", "核销时间", "订单金额", "收银台通道费金额", "佣金金额",
            "结算金额", "带货抽佣", "核销状态 1核销 2退款", "支付金额", "零元单调整款",
            "优惠券唯一id1", "优惠券1分摊订单金额", "优惠券唯一id2", "优惠券2分摊订单金额"
    };
}
