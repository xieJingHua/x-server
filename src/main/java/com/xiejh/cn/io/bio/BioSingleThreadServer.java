package com.xiejh.cn.io.bio;

import java.io.*;
import java.net.Socket;

/**
 * Author xiejh
 * Date   2019/4/9 11:41
 **/
public class BioSingleThreadServer extends BioServer {

    @Override
    protected void handleConnection(Socket socket){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(socket.getOutputStream())) {
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
                pw.println("received info:" + line);
                pw.flush();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
