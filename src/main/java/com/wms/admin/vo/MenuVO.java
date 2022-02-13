package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
public class MenuVO implements Comparable<MenuVO> {
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

    private String path;

    private String component;

    private String redirect;


    /**
     * 展示顺序
     */
    private Integer seq;
    /**
     * 资源标签
     */
    private String label;

    /**
     * 资源类型 0目录 1 菜单 2 资源
     */
    private String type;

    /**
     * 图标
     */
    private String icon;
    /**
     * 状态  1 启用 0 停用
     */
    private String status;
    /**
     * 是否隐藏 0 否 1 是
     */
    private String hidden;

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

    private Set<MenuVO> children = new TreeSet<>();


    @Override
    public int compareTo(MenuVO o) {
        if(this.levelNo!=o.levelNo){
            return this.levelNo-o.levelNo;
        }
        return this.seq -o.getSeq();
    }
}
