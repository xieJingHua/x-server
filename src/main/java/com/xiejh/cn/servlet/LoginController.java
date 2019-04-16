package com.xiejh.cn.servlet;

import com.xiejh.cn.annotations.RequestMapping;
import com.xiejh.cn.system.HttpRequest;
import com.xiejh.cn.system.HttpResponse;
import com.xiejh.cn.system.HttpServlet;

import java.io.IOException;

/**
 * Author xiejh
 * Date   2019/4/14 18:22
 **/
@RequestMapping("/login")
public class LoginController  extends HttpServlet {
    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
          System.out.println("login...");
        try {
            response.write("login success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {

    }
}
