package com.xiejh.cn.io.nio;

import com.xiejh.cn.system.ApplicationConfig;
import com.xiejh.cn.system.Constants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 接收者
 * @author xiejh
 * @Date 2020/5/3 20:12
 **/
public class Reader implements Runnable {

    Selector serverSelector;
    Selector readSelector;
    Selector writeSelector;


    public Reader(Selector serverSelector, Selector readSelector, Selector writeSelector) {
        this.serverSelector = serverSelector;
        this.readSelector = readSelector;
        this.writeSelector = writeSelector;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为1ms
                if (readSelector.select(1) > 0) {
                    Set<SelectionKey> set = readSelector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = set.iterator();

                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();

                        if (key.isReadable()) {
                            try {
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                // (3) 读取数据以块为单位批量读取
                                clientChannel.read(byteBuffer);
                                byteBuffer.flip();
                                System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer)
                                        .toString());
                            } finally {
                                keyIterator.remove();
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }

                    }
                }
            }
        } catch (IOException ignored) {

        }
    }
}
