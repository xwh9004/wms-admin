package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
@TableName("t_wms_role")
public class RoleEntity extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId("id")
    private String id;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 角色代码
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 角色类型
     */
    @TableField("type")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
            "id=" + id +
            ", roleName=" + roleName +
            ", roleCode=" + roleCode +
            ", type=" + type +
        "}";
    }
}
