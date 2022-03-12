package com.wms.admin.dto;

import com.wms.admin.vo.BaseVO;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**

 *
 * @author Jesse
 * @since 2022-02-26 14:22:48
 */
@Data
public class BulletinInfoDto extends BaseVO {

    private Integer id;

    /**
     * 公告内容
     */
    private String bulletinInfo;

    /**
     * 是否发布
     */
    private String isPublish;




}
