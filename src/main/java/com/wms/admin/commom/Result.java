package com.wms.admin.commom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Result {

    public static String SUCCESS(Object data){
        return JSON.toJSONString(data);
    }
}
