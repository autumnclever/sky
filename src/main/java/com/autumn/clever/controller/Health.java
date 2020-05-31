package com.autumn.clever.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zhangqiuying
 * @Date: 2020/5/31 16:51
 */
@Controller
public class Health {

    @ResponseBody
    @RequestMapping("/health.json")
    public String getHealth() {
        return "hello world";
    }
}
