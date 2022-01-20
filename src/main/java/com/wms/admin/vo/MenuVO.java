package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MenuVO {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 菜单名称
     */

    private String menuName;

    /**
     * 菜单代码
     */
    private String menuCode;

    /**
     * 父ID
     */
    private String parentId;

    /**
     * 菜单层级路径
     */
    private String levelPath;

    /**
     * 菜单层级
     */
    private Integer levelNo;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 展示顺序
     */
    private Integer seq;

    /**
     * 资源类型 1 菜单 2 按钮
     */
    private String type;

    /**
     * 创建人
     */

    private String createBy;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    /**
     * 最后更新人
     */

    private String updateBy;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

    private List<MenuVO> children = new ArrayList<>();



}