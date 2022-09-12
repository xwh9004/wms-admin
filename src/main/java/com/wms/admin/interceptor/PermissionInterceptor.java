package com.wms.admin.interceptor;

import com.wms.admin.auth.UserInfo;
import com.wms.admin.auth.UserInfoContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author: xwh90
 * @date: 2022/9/12 12:48
 * @description:
 */
@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri =request.getRequestURI();
        final UserInfo userInfo = UserInfoContext.getUserInfo();
        if(Objects.nonNull(userInfo)){

        }
        return true;
    }
}
