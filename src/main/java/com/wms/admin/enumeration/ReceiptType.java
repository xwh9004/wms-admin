package com.wms.admin.enumeration;

public enum ReceiptType {
    STORAGE_IN("RK"),
    STORAGE_OUT("CK"),
    STORAGE_SHIFT("DB"),
    DISCARD("BF"),
    INVENTORY("PD") ;

    private String type ;
    private ReceiptType(String type){
        this.type = type;
    }

    public String type() {
        return type;
    }
}
