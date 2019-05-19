package com.xiejh.cn.io.bio;

import com.xiejh.cn.Bootstrap;
import com.xiejh.cn.system.Server;
import com.xiejh.cn.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author xiejh
 * Date   2019/4/9 11:38
 **/
public abstract class BioServer implements Server {

    private static Logger logger = LoggerFactory.getLogger(BioServer.class);


    protected static final int PORT = 8090;

    /**
     * 启动服务器
     */
    @Override
    public void start() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
            logger.info("line:{} - x-server started",LogUtil.getLineNumber());
            while (true) {
                Socket socket = server.accept();
                logger.debug("a new connection come in!");
                handleConnection(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理新客户端的连接请求
     *
     * @param socket
     */
    protected void handleConnection(Socket socket) {
        //字类必须重写，否则抛出异常
        throw new UnsupportedOperationException();
    }
}
