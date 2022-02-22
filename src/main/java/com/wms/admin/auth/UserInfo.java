package com.wms.admin.auth;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
    private String userId;
    private String username;
    private List<String> roleIds;
    private String password;
    private List<String> resources;
}
