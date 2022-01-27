package com.wms.admin.interceptor;

import com.wms.admin.auth.Audience;
import com.wms.admin.auth.JwtTokenUtil;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {
    @Autowired
    private Audience audience;

    /**
     * jwt 登录拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        log.info(request.getRequestURI());
        // 获取请求头信息authorization信息
        final String token = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
//        log.info("## authHeader= {}", authHeader);
        if (StringUtils.isBlank(token)) {
            log.info("### 用户未登录，请先登录 ###");
            throw new BusinessException(ResultCode.USER_NOT_LOGGED_IN);
        }
        // 获取token
        if (audience == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            audience = (Audience) factory.getBean("audience");
        }
        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
        JwtTokenUtil.parseJWT(token, audience.getBase64Secret());
        return true;
    }
}
