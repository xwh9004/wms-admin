package com.wms.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.entity.MenuEntity;
import com.wms.admin.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-01-19 16:02:56
 */
public interface IMenuService extends IService<MenuEntity> {

     List<MenuVO> queryList();

     boolean addMenu(MenuVO menuVO);

     boolean updateMenu(MenuVO menuVO);

     boolean deleteMenu(String  id);

    boolean addTopMenu(MenuVO menuVO);


}
