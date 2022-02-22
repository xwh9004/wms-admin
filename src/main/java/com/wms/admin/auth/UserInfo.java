package com.wms.admin.auth;

import com.wms.admin.vo.UserRoleVO;
import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
    private String userId;
    private String username;
    private List<UserRoleVO> roles;
    private String password;
    private List<String> resources;
}
