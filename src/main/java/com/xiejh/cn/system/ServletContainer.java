package com.xiejh.cn.system;

import com.xiejh.cn.annotations.RequestMapping;
import com.xiejh.cn.servlet.HomeController;
import com.xiejh.cn.servlet.LoginController;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author xiejh
 * Date   2019/4/14 18:18
 **/
public class ServletContainer {

    private static final String SCAN_PACKAGE = "scanPackage";

    private volatile static ServletContainer instance;

    private Map<String, Class> container = new ConcurrentHashMap<>();

    private ApplicationConfig applicationConfig = ApplicationConfig.getInstance();

    private ServletContainer() {
    }

    public static ServletContainer getInstance() {
        if (instance == null) {
            synchronized (ServletContainer.class) {
                if (instance == null) {
                    instance = new ServletContainer();
                }
            }
        }
        return instance;
    }

    public void init() throws Exception {
        String scanPackage = applicationConfig.getProperty(SCAN_PACKAGE);
        String slashPath = scanPackage.replace(".", "/");
        URL url = this.getClass().getClassLoader().getResource(slashPath);
        String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
        File directory = new File(filePath);
        if (!directory.isDirectory()) {
            throw new Exception("the config of 'scanPackage' is not a directory!");
        }
        File[] files = directory.listFiles(((dir, name) -> {
            if (name.endsWith(".class")) {
                return true;
            }
            return false;
        }));
        for (File file : files) {
            String className = file.getName().substring(0, file.getName().indexOf("."));
            Class clazz = Class.forName(scanPackage + "." + className);
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
                  System.out.println("url:" + requestMapping.value()+", controller:"+clazz.toString());
                container.put(requestMapping.value(), clazz);
            }
        }
        System.out.println("init container success!");
    }

    public void dispatch(HttpRequest request, HttpResponse response) throws IllegalAccessException, InstantiationException {
        Class httpServletClass = container.get(request.getUrl());
        System.out.println("thread:" + Thread.currentThread().getName() + ", url:" + request.getUrl()
                + ", controller:" + (httpServletClass != null ? httpServletClass.toString() : DefaultHttpServlet.class.toString()));
        if (httpServletClass != null) {
            HttpServlet httpServlet = (HttpServlet) httpServletClass.newInstance();
            httpServlet.service(request, response);
        } else {
            DefaultHttpServlet.getInstance().service(request, response);
        }
    }
}
