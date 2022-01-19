package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

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

    /**
     * 展示顺序
     */
    @TableField("seq")
    private Integer seq;

    /**
     * 资源类型 1 菜单 2 按钮
     */
    @TableField("type")
    private String type;

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
            ", seq=" + seq +
            ", type=" + type +
        "}";
    }
}
