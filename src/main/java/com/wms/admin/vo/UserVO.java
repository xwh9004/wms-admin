package com.wms.admin.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {

    private String id;
    private String username;
    private String password;
    private String roleDesc;
    private List<String> roleIds;
}
