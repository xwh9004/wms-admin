package com.wms.admin.auth;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
    private String userId;
    private String username;
    private String roleCode;
    private String password;
    private List<String> resources;
}
