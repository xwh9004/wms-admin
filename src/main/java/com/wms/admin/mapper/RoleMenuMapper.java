package com.wms.admin.mapper;

import com.wms.admin.auth.PermissionResource;
import com.wms.admin.entity.RoleMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenuEntity> {

    List<String> roleResources(@Param("roleId") String roleId);

    List<PermissionResource> rolePermissions(@Param("roleId") String roleId);
}
