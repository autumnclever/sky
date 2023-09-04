package com.autumn.clever.baidu.cashier;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-08-10 22:45
 **/
public class ServiceKeyword {

    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 功能名称
     */
    private String funcName;
    /**
     * 异常名称
     */
    private String exceptionName;
    /**
     * 关键字
     */
    private String keyword;

    public ServiceKeyword(String moduleName, String funcName, String exceptionName) {
        this.moduleName = moduleName;
        this.funcName = funcName;
        this.exceptionName = exceptionName;
        this.keyword = moduleName + "_" + funcName + "_" + exceptionName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getFuncName() {
        return funcName;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public String getKeyword() {
        return keyword;
    }

}
