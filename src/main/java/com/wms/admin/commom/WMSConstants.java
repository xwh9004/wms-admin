package com.wms.admin.commom;

import java.math.BigDecimal;

public interface WMSConstants {

    String REQUEST_NO = "requestNo";

    /**
     * 不删除
     */
     String DEL_FLG_N ="1";
    /**
     * 已删除
     */
     String DEL_FLG_Y ="0";
    /**
     * 以提交
     */
    String STATUS_1 = "1";
    /**
     * 以保存
     */
     String STATUS_0= "0";

     String TAKE_IN_INIT = "0";
     String TAKEN_IN= "1";
    String TAKE_OUT_INIT = "0";
    String TAKEN_OUT= "1";
    /**
     * 合同未生效
     */
     String CONTRACT_EDITABLE = "0";
    /**
     * 合同已生效
     */
     String CONTRACT_EFFECT= "1";
    /**
     * 合同已失效
     */
     String CONTRACT_INEFFECTIVE= "2";
    /**
     * 合同已国期
     */
     String CONTRACT_EXPIRED= "3";

     String MENU_TYPE_DIR="0";
     String MENU_TYPE_MENU ="1";
     String MENU_TYPE_RESOURCE ="2";
     BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

}
