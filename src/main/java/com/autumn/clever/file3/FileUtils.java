package com.autumn.clever.file3;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.io.File;

/**
 * @program: sky
 * @description: 文件工具类
 * @author: zhangqiuying
 * @create: 2023-07-11 10:53
 **/
@Slf4j
public class FileUtils {

    /**
     * 删除文件
     *
     * @param filePath 文件目录路径
     */
    public static void deleteFile(String filePath) {
        try {
            if (Strings.isBlank(filePath)) {
                return;
            }
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            log.error("FileUtil_deleteFilePath_skyException, filePath: {}", filePath, e);
        }
    }

    public static void deleteFile(File file) {
        try {

            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            log.error("FileUtil_deleteFile_skyException", e);
        }
    }
}
