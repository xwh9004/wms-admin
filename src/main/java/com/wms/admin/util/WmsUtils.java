package com.wms.admin.util;

import com.wms.admin.commom.ResultCode;
import com.wms.admin.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

public class WmsUtils {

    public static void checkDateRange(LocalDateTime startTime, LocalDateTime endTime) {
        if(Objects.nonNull(startTime) && Objects.nonNull(endTime)){
            if(startTime.isAfter(endTime)){
                throw new BusinessException(ResultCode.PARAM_ERROR,"开始时间不能晚于结束时间");
            }
        }
    }

    /**
     * 获取客户端的IP 地址
     *
     * @param request HttpServletRequest
     * @return IP 地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
