package com.xiejh.cn.servlet;
import java.io.IOException;

import com.xiejh.cn.annotations.RequestMapping;
import com.xiejh.cn.system.HttpRequest;
import com.xiejh.cn.system.HttpResponse;
import com.xiejh.cn.system.HttpServlet;

/**
 * Author xiejh
 * Date   2019/4/14 18:23
 **/
@RequestMapping("/home")
public class HomeController extends HttpServlet {

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
          System.out.println("home page");
        try {
            response.write("welcome, this is home page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {

    }
}
