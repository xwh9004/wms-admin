package com.wms.admin.servlet;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: xwh90
 * @date: 2022/8/25 17:19
 * @description:
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private String body;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        this.body = readRequestBody(request);
    }

    private String readRequestBody(HttpServletRequest request) {
        char[] buffer = new char[128];
        StringBuilder sb = new StringBuilder();

        try (ServletInputStream in = request.getInputStream();
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(in, request.getCharacterEncoding()));) {
            int len = -1;
            while ((len = reader.read(buffer)) > 0) {
                sb.append(buffer, 0, len);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }

    public String getBody() {
        return body;
    }
}
