package com.wms.admin.mapper;

import com.wms.admin.entity.MenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-01-19 16:02:56
 */
public interface MenuMapper extends BaseMapper<MenuEntity> {

    List<MenuEntity> roleMenus(@Param("roleId") String roleId);
    List<MenuEntity> userMenus(@Param("roleId") String userId);

}
