package com.wms.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiptUtil {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static String generateNo(String type){
      return   type.concat(dateFormat.format(new Date()));
    }
}
