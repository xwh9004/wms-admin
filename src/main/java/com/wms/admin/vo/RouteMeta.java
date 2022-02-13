package com.wms.admin.vo;

import lombok.Data;

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html

 * meta : {
 roles: ['admin','editor']    control the page roles (you can set multiple roles)
 title: 'title'               the name show in sidebar and breadcrumb (recommend set)
 icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
 noCache: true                if set true, the page will no be cached(default is false)
 affix: true                  if set true, the tag will affix in the tags-view
 breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
 activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
 }
 */
@Data
public class RouteMeta {
    private String title;
    private String icon;
//    private String affix;
//    private String breadcrumb ="false";
//    private String noCache ="true";
    private String activeMenu;

    private RouteMeta(){

    }

    public static RouteMeta build(MenuVO menu){
        RouteMeta metaVO = new RouteMeta();
//        metaVO.setAffix("true");
        metaVO.setIcon(menu.getIcon());
        metaVO.setTitle(menu.getMenuName());
//        metaVO.setNoCache("true");
//        metaVO.setBreadcrumb("false");
//            metaVO.setActiveMenu("false");
        return metaVO;
    }
}
