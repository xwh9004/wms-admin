package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import com.wms.admin.vo.OrgVO;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 组织表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
@TableName("t_wms_org")
public class OrgEntity extends BaseEntity {

    @TableId(value = "id")
    private String id;

    /**
     * 机构名称
     */
    @TableField("org_name")
    private String orgName;

    /**
     * 机构代码
     */
    @TableField("org_code")
    private String orgCode;

    /**
     * 父ID
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 路径层级
     */
    @TableField("level_path")
    private String levelPath;

    /**
     * 顺序
     */
    @TableField("seq")
    private Integer seq;

    /**
     * 层级
     */
    @TableField("level_no")
    private Integer levelNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    @Override
    public String toString() {
        return "OrgInfoEntity{" +
                "id=" + id +
                ", orgName=" + orgName +
                ", orgCode=" + orgCode +
                ", parentId=" + parentId +
                ", levelPath=" + levelPath +
                ", seq=" + seq +
                ", levelNo=" + levelNo +
                "}";
    }

    public OrgVO toOrgVO() {
        OrgVO orgVO = new OrgVO();
        BeanUtils.copyProperties(this, orgVO);
        return orgVO;
    }
}
