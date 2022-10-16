package com.wms.admin.interceptor;

import com.wms.admin.auth.UserInfo;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: xwh90
 * @date: 2022/9/12 12:48
 * @description:
 */
@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Value("${server.servlet.context-path}")
    private String context;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith(context)) {
            String path = requestURI.substring(context.length());
            final UserInfo userInfo = UserInfoContext.getUserInfo();
            if (Objects.nonNull(userInfo)) {
                final List<String> authorityUrls = userInfo.getAuthorityUrls();
                if (CollectionUtils.isEmpty(authorityUrls)) {
                    throw new BusinessException(ResultCode.PERMISSION_DENIED);
                }
                final List<String> matched = authorityUrls
                        .stream()
                        .filter(url -> antPathMatcher.match(url, path))
                        .collect(Collectors.toList());
                if (CollectionUtils.isEmpty(matched)) {
                    throw new BusinessException(ResultCode.PERMISSION_DENIED);
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }
}
