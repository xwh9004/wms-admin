package com.wms.admin.filter;

import com.wms.admin.commom.WMSConstants;
import com.wms.admin.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设置请求唯一标识，用于链路跟踪
 */
@Component
@Slf4j
public class LoggingAccessFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        MDC.put(WMSConstants.REQUEST_NO, UUIDUtil.uuid());
        try {
            httpServletResponse.addHeader(WMSConstants.REQUEST_NO,MDC.get(WMSConstants.REQUEST_NO));
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            MDC.remove(WMSConstants.REQUEST_NO);
            MDC.clear();
        }
    }
}
