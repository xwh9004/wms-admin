package com.wms.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private String id;
    private String userName;
    private String password;
    private String deptName;
    private String updateBy;
    private List<String> roleIds;
    private String roleId;
}
