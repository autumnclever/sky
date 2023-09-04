package com.autumn.clever.baidu.cashier;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * @program: sky
 * @description: ServiceRuntimeException
 * @author: zhangqiuying
 * @create: 2023-08-10 22:43
 **/
public class ServiceRuntimeException extends RuntimeException{

    /**
     * 序列号
     */
    private static final long serialVersionUID = 4207282791603332262L;

    /**
     * 异常关键字定义
     */
    private ServiceKeyword keyword;

    private final Map<String, Object> params = new TreeMap<String, Object>();

    public ServiceRuntimeException(ServiceKeyword keyword, String message) {
        super(message);
        this.keyword = keyword;
    }

    public ServiceRuntimeException(ServiceKeyword keyword, String message, Throwable cause) {
        super(message, cause);
        this.keyword = keyword;
    }

    public ServiceRuntimeException(ServiceKeyword keyword, Throwable cause) {
        super(cause);
        this.keyword = keyword;
    }

    public ServiceRuntimeException setParam(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public ServiceKeyword getKeyword() {
        return keyword;
    }

    @Override
    public void printStackTrace(PrintStream s) {
        printStackTrace(new PrintWriter(s));
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        synchronized (this) {
            s.println(this);
            s.println(keyword.getKeyword());
            s.print("\t-------------------------------");
            for (String key : params.keySet()) {
                s.print(String.format("\t%s=[%s]", key, params.get(key)));
            }
            StackTraceElement[] trace = getStackTrace();
            for (StackTraceElement aTrace : trace) {
                s.println("\tat " + aTrace);
            }
            Throwable ourCause = getCause();
            if (ourCause != null) {
                ourCause.printStackTrace(s);
            }
            s.flush();
        }
    }
}
