package com.wms.admin.vo;

import com.wms.admin.commom.WMSConstants;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Data
public class MenuVO extends BaseVO implements Comparable<MenuVO> {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    /**
     * 菜单代码
     */
    @NotBlank(message = "菜单代码不能为空")
    private String menuCode;

    /**
     * 父ID
     */
    @NotBlank(message = "父ID不能为空")
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
    @Min(value = 0,message = "seq不能小于0")
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

    private Set<MenuVO> children = new TreeSet<>();


    @Override
    public int compareTo(MenuVO o) {
        if (this.levelNo != o.levelNo) {
            return this.levelNo - o.levelNo;
        }
        return this.seq - o.getSeq();
    }

    public RouteVO toRoute(){
        RouteVO routeVO = new RouteVO();
        routeVO.setId(this.id);
        routeVO.setPath(this.path);
        if (WMSConstants.MENU_TYPE_DIR.equals(this.type)) {
            routeVO.setComponent("layout/Layout");
        } else {
            routeVO.setComponent(this.component);
            routeVO.setName(this.menuName);
        }
        routeVO.setMeta(RouteMeta.build(this));

        if ("0".equals(this.hidden)) {
            routeVO.setHidden(false);
        } else {
            routeVO.setHidden(true);
        }
        routeVO.setRedirect(this.redirect);
        return routeVO;
    }
}
