package com.xiejh.cn.system;

/**
 * Author xiejh
 * Date   2019/4/14 18:12
 **/
public abstract class HttpServlet {

    public void service(HttpRequest request,HttpResponse response){
        if("GET".equals(request.getMethod())){
            doGet(request,response);
        }else if("POST".equals(request.getMethod())){
            doPost(request,response);
        }
    }

    public abstract void doGet(HttpRequest request,HttpResponse response);

    public abstract void doPost(HttpRequest request,HttpResponse response);
}
