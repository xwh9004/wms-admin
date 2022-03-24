package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
@TableName("t_wms_user")
public class UserEntity extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId("id")
    private String id;

    /**
     * 用户名称
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户密码
     */
    @TableField("user_pwd")
    private String userPwd;

    /**
     * 部门名称
     */
    @TableField("dept_name")
    private String deptName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
            "id=" + id +
            ", userName=" + userName +
            ", userPwd=" + userPwd +
            ", deptName=" + deptName +
        "}";
    }
}
