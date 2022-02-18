package com.wms.admin.exception;

import com.wms.admin.commom.Result;
import com.wms.admin.commom.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception exception) {
        log.error("执行出错",exception);
        String msg = exception.getMessage();
        return Result.error(ResultCode.SYS_ERROR.getCode(),msg);
    }

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Result businessExceptionHandler(BusinessException exception) {
        return Result.error(exception.getCode(), exception.getMessage());
    }
}
