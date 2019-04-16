package com.xiejh.cn.servlet;

import com.xiejh.cn.annotations.RequestMapping;
import com.xiejh.cn.system.HttpRequest;
import com.xiejh.cn.system.HttpResponse;
import com.xiejh.cn.system.HttpServlet;

import java.io.IOException;

/**
 * Author xiejh
 * Date   2019/4/15 23:05
 **/
@RequestMapping("/user")
public class UserController extends HttpServlet {

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        try {
            response.write("this is user page!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {

    }
}
