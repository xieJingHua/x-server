package com.xiejh.cn.system;

import java.io.IOException;

/**
 * Author xiejh
 * Date   2019/4/14 21:53
 **/
public class DefaultHttpServlet extends  HttpServlet{

    private DefaultHttpServlet(){}

    private static class Inner{
        private static DefaultHttpServlet INSTANCE=new DefaultHttpServlet();
    }

    public static DefaultHttpServlet getInstance(){
        return Inner.INSTANCE;
    }

    @Override
    public void doGet(HttpRequest request, HttpResponse response){
        try {
              System.out.println("this is the default com.xiejh.cn.servlet!");
            response.write("this is the default com.xiejh.cn.servlet!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        try {
              System.out.println("this is the default com.xiejh.cn.servlet!");
            response.write("this is the default com.xiejh.cn.servlet!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
