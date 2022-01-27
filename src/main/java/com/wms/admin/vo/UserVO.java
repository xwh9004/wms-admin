package com.wms.admin.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {

    private String userId;
    private String username;
    private String userPwd;
    private List<String> roleIds;
}
