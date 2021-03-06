package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Jesse
 * @since 2022-01-19 16:02:56
 */
@TableName("t_wms_menu")
public class MenuEntity extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId("id")
    private String id;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 菜单代码
     */
    @TableField("menu_code")
    private String menuCode;

    /**
     * 父ID
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 菜单层级路径
     */
    @TableField("level_path")
    private String levelPath;

    /**
     * 菜单层级
     */
    @TableField("level_no")
    private Integer levelNo;

    /**
     * 菜单路径
     */
    @TableField("url")
    private String url;

    @TableField("redirect")
    private String redirect;

    @TableField("path")
    private String path;

    @TableField("component")
    private String component;

    /**
     * 展示顺序
     */
    @TableField("seq")
    private Integer seq;

    /**
     * 资源类型 0目录 1 菜单 2 资源
     */
    @TableField("type")
    private String type;

    /**
     * 是否隐藏 0 不隐藏 1 隐藏
     */
    @TableField("hidden")
    private String hidden;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;
    /**
     * 状态  1 启用 0 停用
     */
    @TableField("status")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getLevelPath() {
        return levelPath;
    }

    public void setLevelPath(String levelPath) {
        this.levelPath = levelPath;
    }
    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
            "id=" + id +
            ", menuName=" + menuName +
            ", menuCode=" + menuCode +
            ", parentId=" + parentId +
            ", levelPath=" + levelPath +
            ", levelNo=" + levelNo +
            ", url=" + url +
            ", path=" + path +
            ", component=" + component +
            ", redirect=" + redirect +
            ", seq=" + seq +
            ", status=" + status +
            ", type=" + type +
            ", hidden=" + hidden +
            ", icon=" + icon +
        "}";
    }

}
