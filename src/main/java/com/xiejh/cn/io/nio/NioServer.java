package com.xiejh.cn.io.nio;

import com.xiejh.cn.system.ApplicationConfig;
import com.xiejh.cn.system.Constants;
import com.xiejh.cn.system.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiejh
 * @Date 2020/5/3 9:47
 **/
public class NioServer implements Server {

    protected ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public void start() {
        try {
            Selector serverSelector = Selector.open();
            Selector readSelector = Selector.open();
            Selector writeSelector = Selector.open();

            executorService.execute(() -> {

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
