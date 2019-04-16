package com.xiejh.cn.system;

/**
 * Author xiejh
 * Date   2019/4/14 18:16
 **/
public class ServletMapping {

    private String pattern;

    private HttpServlet clazz;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public HttpServlet getClazz() {
        return clazz;
    }

    public void setClazz(HttpServlet clazz) {
        this.clazz = clazz;
    }
}
