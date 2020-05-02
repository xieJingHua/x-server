package com.xiejh.cn.io.bio;

import com.xiejh.cn.system.HttpRequest;
import com.xiejh.cn.system.HttpResponse;
import com.xiejh.cn.system.ServletContainer;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author xiejh
 * Date   2019/4/9 13:41
 **/
public class BioMultipleThreadServer extends BioServer {

    protected ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Override
    protected void handleConnection(Socket socket) {
        executorService.execute(new Worker(socket));
    }

    class Worker implements Runnable {
        Socket socket;

        public Worker(Socket socket) {
            this.socket = socket;
        }

        private void communicate() {
            try {
                HttpRequest request=new HttpRequest(socket.getInputStream());
                if(request.getUrl()==null){
                    System.out.println("空连接");
                    return;
                }

                HttpResponse response=new HttpResponse(socket.getOutputStream());
                ServletContainer.getInstance().dispatch(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        @Override
        public void run() {
            communicate();
        }
    }
}
