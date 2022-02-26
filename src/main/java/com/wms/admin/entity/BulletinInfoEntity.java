package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 布告信息表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:48
 */
@TableName("t_wms_bulletin_info")
public class BulletinInfoEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公告内容
     */
    @TableField("BULLETIN_INFO")
    private String bulletinInfo;

    /**
     * 是否发布
     */
    @TableField("is_publish")
    private String isPublish;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getBulletinInfo() {
        return bulletinInfo;
    }

    public void setBulletinInfo(String bulletinInfo) {
        this.bulletinInfo = bulletinInfo;
    }
    public String getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish;
    }

    @Override
    public String toString() {
        return "BulletinInfoEntity{" +
            "id=" + id +
            ", bulletinInfo=" + bulletinInfo +
            ", isPublish=" + isPublish +
        "}";
    }
}
