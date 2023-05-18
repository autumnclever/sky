package com.autumn.clever.goodcoder;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-05-08 14:51
 **/
public class MyApplicationEventListener implements ApplicationListener {


    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        // 在这里可以监听到Spring Boot的生命周期
        if (applicationEvent instanceof ApplicationEnvironmentPreparedEvent) { // 初始化环境变量
        } else if (applicationEvent instanceof ApplicationPreparedEvent) { // 初始化完成
        } else if (applicationEvent instanceof ContextRefreshedEvent) {  // 应用刷新
        } else if (applicationEvent instanceof ApplicationReadyEvent) {// 应用已启动完成
        } else if (applicationEvent instanceof ApplicationStartingEvent) {  //应用启动，需要在代码动态添加监听器才可捕获
        } else if (applicationEvent instanceof ContextStoppedEvent) {  // 应用停止
        } else if (applicationEvent instanceof ContextClosedEvent) {   // 应用关闭
        } else {
        }
    }
}