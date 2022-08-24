package com.wms.admin.util;

import com.wms.admin.commom.ResultCode;
import com.wms.admin.exception.BusinessException;

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
}
