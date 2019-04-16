package com.xiejh.cn.io.bio;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author xiejh
 * Date   2019/4/9 11:40
 **/
public class BioClient {

    public static void main(String[] args) {
        new BioClient().start();
    }

    public void start() {
        try (Socket socket = new Socket("localhost", 8080)){
            try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter pw = new PrintWriter(socket.getOutputStream())) {
                Scanner scanner = new Scanner(System.in);
                  System.out.println("客户端已启动，请输入：");
                if (scanner.hasNext()) {
                    String next = scanner.next();
                    pw.println(next);
                    pw.flush();
                    System.out.println(br.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
