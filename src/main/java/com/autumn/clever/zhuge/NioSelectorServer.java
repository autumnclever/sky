package com.autumn.clever.zhuge;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/8/30 1:42 下午
 */
public class NioSelectorServer {
    Selector selector = Selector.open();

    public NioSelectorServer() throws IOException {

        selector.select();
    }
}
