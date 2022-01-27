package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
@TableName("t_wms_role_menu")
public class RoleMenuEntity extends BaseEntity {

    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenuEntity{" +
            "roleId=" + roleId +
            ", menuId=" + menuId +
        "}";
    }
}
