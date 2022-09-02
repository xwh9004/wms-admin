package com.wms.admin.filter;

import com.wms.admin.servlet.RequestWrapper;
import com.wms.admin.servlet.ResponseWrapper;
import com.wms.admin.util.WmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

/**
 * @author: xwh90
 * @date: 2022/8/25 17:13
 * @description:
 */
@Slf4j
@Component
public class LoggingPrinterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String clientId = WmsUtils.getIpAddress(request);
        log.info("request clientIp :{},method={},uri={}", clientId, request.getMethod(), request.getRequestURI());
        if (HttpMethod.GET.matches(request.getMethod())) {
            log.info("request queryString :{}", request.getQueryString());
        } else {
            if (servletRequest instanceof RequestWrapper) {
                RequestWrapper requestWrapper = (RequestWrapper) servletRequest;
                log.info("request queryString: {} body :{}", requestWrapper.getQueryString(),requestWrapper.getBody());
            }
        }
        filterChain.doFilter(request, servletResponse);
        if (servletResponse instanceof ResponseWrapper) {
            ResponseWrapper responseWrapper = (ResponseWrapper) servletResponse;
            if(responseWrapper.getContentType().equals("application/json")){
                log.info("response body :{}",new String(responseWrapper.getResponseBody()));
            }
        }
    }


}
