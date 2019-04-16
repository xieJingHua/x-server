package com.xiejh.cn.system;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Author xiejh
 * Date   2019/4/14 18:06
 **/
public class HttpResponse {

    private OutputStream os;

    public HttpResponse(OutputStream os) {
        this.os = os;
    }

    public void write(String content) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK").append("\n").append("content-type:text/html")
                .append("\n\n").append("<html><head><title>x-server</title></head><body>")
                .append(content).append("</body></html>");
        os.write(sb.toString().getBytes());
        os.close();
    }

    public OutputStream getOs() {
        return os;
    }

    public void setOs(OutputStream os) {
        this.os = os;
    }
}
