package com.wms.admin.filter;

import com.alibaba.fastjson.JSON;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.servlet.RequestWrapper;
import com.wms.admin.servlet.ResponseWrapper;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.util.WmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

/**
 * 设置请求唯一标识，用于链路跟踪
 */
@Component
@Slf4j
public class LoggingAccessFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MDC.put(WMSConstants.REQUEST_NO, UUIDUtil.uuid());
        try {
            response.addHeader(WMSConstants.REQUEST_NO, MDC.get(WMSConstants.REQUEST_NO));
            RequestWrapper requestWrapper = new RequestWrapper(request);
            ResponseWrapper responseWrapper = new ResponseWrapper(response);
            filterChain.doFilter(requestWrapper, responseWrapper);
            //必须设置ContentLength
            response.setContentLength(responseWrapper.toByteArray().length);
            //根据http accept来设置，我这里为了简便直接写json了
            response.setContentType("application/json;charset=utf-8");
            response.getOutputStream().write(responseWrapper.toByteArray());
        } finally {
            MDC.remove(WMSConstants.REQUEST_NO);
            MDC.clear();
        }
    }

}
