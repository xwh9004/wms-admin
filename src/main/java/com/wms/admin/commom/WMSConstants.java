package com.wms.admin.commom;

import java.math.BigDecimal;

public interface WMSConstants {

    public static String REQUEST_NO = "requestNo";


    /**
     * 不删除
     */
    public static final String DEL_FLG_N ="1";
    /**
     * 已删除
     */
    public static final String DEL_FLG_Y ="0";
    public static final String STATUS_1 = "1";
    public static final String STATUS_0= "0";

    public static final String TAKE_IN_INIT = "0";
    public static final String TAKEN_IN= "1";

    /**
     * 合同未生效
     */
    public static final String CONTRACT_EDITABLE = "0";
    /**
     * 合同已生效
     */
    public static final String CONTRACT_EFFECT= "1";
    /**
     * 合同已失效
     */
    public static final String CONTRACT_INEFFECTIVE= "2";
    /**
     * 合同已国期
     */
    public static final String CONTRACT_EXPIRED= "3";

    public static final String MENU_TYPE_DIR="0";
    public static final String MENU_TYPE_MENU ="1";
    public static final String MENU_TYPE_RESOURCE ="2";
    public static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

}
