package com.xiejh.cn.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Author xiejh
 * Date   2019/4/14 17:45
 **/
public class HttpRequest {
    //socket传过来的输入流
    private InputStream is;
    private String url;
    private String method;
    private Map<String, Object> data;
    private String protocolVersion;

    public HttpRequest(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        String request = "";
        int length = 0;
        if ((length = is.read(bytes)) > 0) {
            request = new String(bytes, 0, length);
        }
        System.out.println(request);
        String[] requestArray = request.split("\n");
        String[] firstLines = requestArray[0].split("\\s");
          System.out.println(requestArray[0]);
        method = firstLines[0];
//        if(firstLines[1].contains("?")){
//            firstLines[1].split("?");
//        }
        url = firstLines[1];
        protocolVersion = firstLines[2];
    }


    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }
}
