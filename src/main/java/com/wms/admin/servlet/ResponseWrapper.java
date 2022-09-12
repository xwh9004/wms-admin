package com.wms.admin.servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author: xwh90
 * @date: 2022/8/25 17:27
 * @description:
 */
public class ResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream bytes = new ByteArrayOutputStream();

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
                bytes.write(b);
            }
        };
        return servletOutputStream;
    }

    public byte[] toByteArray() {
        return bytes.toByteArray();
    }

    public String getResponseBody() {
        return new String(bytes.toByteArray());
    }

//    public ResponseWrapper(HttpServletResponse response) {
//        super(response);
//        output = new ByteArrayOutputStream();
//    }
//    private ByteArrayOutputStream output;
//    private ServletOutputStream filterOutput;
//    /**
//     * 巧妙将ServletOutputStream放到公共变量，解决不能多次读写问题
//     * @return
//     * @throws IOException
//     */
//    @Override
//    public ServletOutputStream getOutputStream() throws IOException {
//        if (filterOutput == null) {
//            filterOutput = new ServletOutputStream() {
//                @Override
//                public void write(int b) throws IOException {
//                    output.write(b);
//                }
//
//                @Override
//                public boolean isReady() {
//                    return false;
//                }
//
//                @Override
//                public void setWriteListener(WriteListener writeListener) {
//                }
//            };
//        }
//        return filterOutput;
//    }
//
//    public byte[] toByteArray() {
//        return output.toByteArray();
//    }
}
