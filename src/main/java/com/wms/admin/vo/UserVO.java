package com.wms.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserVO {
    private String id;
    private String cardNo;
    private String userName;
    private String password;
    private String deptName;
    private String phone;
    private String updateBy;
    private LocalDateTime updateTime;
    private List<String> roleIds;
    private String roleId;
    private String roleName;
}
