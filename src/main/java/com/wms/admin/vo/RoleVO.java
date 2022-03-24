package com.wms.admin.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class RoleVO extends BaseVO{

    private String id;

    @NotNull(message = "角色名称不能为空")
    private String roleName;

    @NotNull(message = "角色代码不能为空")
    private String roleCode;
    /**
     * 1 管理员用户 2普通用户
     * 目前该字段暂无作用
     */
    @NotNull(message = "角色类型不能为空")
    private String type;

    private List<String> menuIds;

}
