package com.wms.admin.vo;

import com.wms.admin.enumeration.ReceiptType;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * 年度入库，出库，调拨，报废统计
 */
@Data
public class AnnualStatisticVO {
    private Integer storageInAmount = Integer.valueOf(0);
    private Integer storageOutAmount=Integer.valueOf(0);
    private Integer storageShiftAmount=Integer.valueOf(0);
    private Integer discardAmount=Integer.valueOf(0);


    public void setAmount(String type, Integer totalAmount){
        if(type.equals(ReceiptType.STORAGE_IN.type())){
              this.storageInAmount =totalAmount;
        }
        if(type.equals(ReceiptType.STORAGE_OUT.type())){
            this.storageOutAmount =totalAmount;
        }
        if(type.equals(ReceiptType.STORAGE_SHIFT.type())){
            this.storageShiftAmount =totalAmount;
        }
        if(type.equals(ReceiptType.DISCARD.type())){
            this.discardAmount =totalAmount;
        }
    }

}
