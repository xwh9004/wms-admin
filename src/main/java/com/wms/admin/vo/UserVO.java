package com.wms.admin.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserVO {
    private String id;
    @NotNull(message = "用户名不能为空")
    private String userName;
    private String password;
    @NotNull(message = "部门编号不能为空")
    private String deptName;
    private String updateBy;
    private LocalDateTime updateTime;
    private List<String> roleIds;
    private String roleId;
    private String roleName;
}
