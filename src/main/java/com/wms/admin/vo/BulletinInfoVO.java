package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**

 *
 * @author Jesse
 * @since 2022-02-26 14:22:48
 */
@Data
public class BulletinInfoVO extends BaseVO {

    private Integer id;

    /**
     * 公告内容
     */
    @Length(max = 500,message = "公告内容最长500个字")
    private String bulletinInfo;

    /**
     * 是否发布
     */
    private String isPublish;




}
