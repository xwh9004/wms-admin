package com.wms.admin.servlet;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.codec.CharEncoding;
import org.zeroturnaround.exec.stream.TeeOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author: xwh90
 * @date: 2022/8/25 17:27
 * @description:
 */
public class ResponseWrapper extends HttpServletResponseWrapper {
    private String responseBody;
    private  ByteOutputStream outputStream = new ByteOutputStream();

    public ResponseWrapper(HttpServletResponse response) {
        super(response);

    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {

        ServletOutputStream servletOutputStream = new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            @Override
            public void write(int b) throws IOException {
                outputStream.write(b);
            }
        };
        return servletOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(new OutputStreamWriter(outputStream, CharEncoding.UTF_8));
    }

    public String getResponseBody() {
        return responseBody;
    }
}
