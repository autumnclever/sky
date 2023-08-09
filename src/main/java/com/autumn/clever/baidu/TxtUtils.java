package com.autumn.clever.baidu;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: sky
 * @description: txt文件处理工具类
 * @author: zhangqiuying
 * @create: 2023-08-03 17:33
 **/
@Slf4j
public class TxtUtils {

    public static List<String> readLinesFromClasspath(String file) {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new ClassPathResource(file).getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                if (StringUtils.isNotBlank(line)) {
                    lines.add(line.trim());
                }
            }
        } catch (Exception e) {
            log.error("read file from classpath error, file:{}", file, e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    log.error("close reader error", e);
                }
            }
        }
        return lines;
    }

    public static void writeLines(List<String> lines, String file, boolean append) {
        if (CollectionUtils.isEmpty(lines) || StringUtils.isBlank(file)) {
            return;
        }
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(file, append),
                            StandardCharsets.UTF_8));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            log.error("write file error, file:{}", file, e);
        }
    }
}
