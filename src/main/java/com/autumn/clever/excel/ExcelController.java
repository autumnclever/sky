package com.autumn.clever.excel;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/4 下午11:16
 */
@Controller
public class ExcelController {
    private static final String[] chTitles = {"电影id", "电影名称", "平均排名", "总入口uv", "总订单提交uv", "支付uv", "订单转化率", "支付转化率", "日期"};
    private static final String[] enTitles = {"movieId", "movieName", "averageSort", "entranceUV", "orderUV", "payUV", "orderConversion", "payConversion", "date"};

    private static final String[] chTitles2 = {"排名", "总入口uv", "总订单提交uv", "支付uv", "订单转化率", "支付转化率", "日期"};
    private static final String[] enTitles2 = {"sort", "entranceUV", "orderUV", "payUV", "orderConversion", "payConversion", "date"};

    @Resource
    FileService fileService;

    @Resource
    DataCalculate dataCalculate;


    @RequestMapping("/calculate.json")
    public Object hotMovies(@RequestParam(name = "clickFile", required = false) MultipartFile clickFile,
                            @RequestParam(name = "saleFile", required = false) MultipartFile saleFile,
                            @RequestParam(name = "date", required = false) String date,
                            HttpServletResponse response) {
        List<SaleMovieDO> saleMovieDOS = fileService.importSaleMovie(saleFile, date);
        List<SaleMovieDO> saleMovieTotals = dataCalculate.calculateMovieSale2(saleMovieDOS, date);

//        List<SaleMovieDO> saleMovieTotals = dataCalculate.calculateMovieSale(saleMovieDOS);
        List<JSONObject> list = Lists.newArrayList();
        saleMovieTotals.stream().forEach(saleMovieDO -> {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(saleMovieDO);
            list.add(jsonObject);
        });
//        XSSFWorkbook workbook = fileService.getXSSFWorkbook(date + "热映列表转化率统计表", chTitles, enTitles, list);
        XSSFWorkbook workbook = fileService.getXSSFWorkbook(date + "热映列表转化率统计表", chTitles2, enTitles2, list);
        exportExcel(response, workbook, "热映列表");
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
