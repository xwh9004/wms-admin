package com.wms.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class RoleVO {

    private String id;

    private String roleName;

    private String roleCode;

    private String type;

    private String updateBy;

    private LocalDateTime updateTime;

    private List<String> menuIds;

}
