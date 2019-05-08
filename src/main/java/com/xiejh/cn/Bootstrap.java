package com.xiejh.cn;

import com.xiejh.cn.io.bio.BioMultipleThreadServer;
import com.xiejh.cn.system.Server;
import com.xiejh.cn.system.ServletContainer;

/**
 * Author xiejh
 * Date   2019/4/9 13:31
 **/
public class Bootstrap {

    public static void main(String[] args) {
        //单线程阻塞server
//        Server server=new BioSingleThreadServer();
        //多线程阻塞server 可连接多个客户端ServletContainer
        try {
            //初始化web容器
            ServletContainer.getInstance().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //启动服务器，开启socket监听（tcp)
        Server server = new BioMultipleThreadServer();
        server.start();
    }
}
