package com.xiejh.cn.io.nio;

import com.xiejh.cn.system.ApplicationConfig;
import com.xiejh.cn.system.Constants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 接收者
 * @author xiejh
 * @Date 2020/5/3 20:12
 **/
public class Acceptor implements Runnable {

    Selector serverSelector;
    Selector readSelector;
    Selector writeSelector;


    public Acceptor(Selector serverSelector, Selector readSelector, Selector writeSelector) {
        this.serverSelector = serverSelector;
        this.readSelector = readSelector;
        this.writeSelector = writeSelector;
    }

    @Override
    public void run() {
        try {
            ServerSocketChannel listenerChannel = ServerSocketChannel.open();
            String port = ApplicationConfig.getInstance().getProperty(Constants.PORT);
            listenerChannel.socket().bind(new InetSocketAddress(Integer.valueOf(port)));
            listenerChannel.configureBlocking(false);
            listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

            while (true) {
                if (serverSelector.select(1) > 0) {
                    Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        if (key.isAcceptable()) {
                            try {
                                SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                clientChannel.configureBlocking(false);
                                clientChannel.register(readSelector, SelectionKey.OP_READ);
                            } finally {
                                iterator.remove();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
