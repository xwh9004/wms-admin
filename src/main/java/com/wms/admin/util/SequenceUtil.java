package com.wms.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SequenceUtil {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static String generateNoByDate(String type){
      return   type.concat(dateFormat.format(new Date()));
    }
}
