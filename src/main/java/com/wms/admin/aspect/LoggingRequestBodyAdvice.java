package com.wms.admin.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author: xwh90
 * @date: 2022/8/26 8:36
 * @description:
 */
//@ControllerAdvice
@Slf4j
public class LoggingRequestBodyAdvice extends RequestBodyAdviceAdapter {
    @Autowired
    private ObjectMapper mapper;


    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("request param:{}", mapper.writeValueAsString(body));
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
