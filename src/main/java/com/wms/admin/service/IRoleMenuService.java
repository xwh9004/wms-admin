package com.wms.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.entity.RoleMenuEntity;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
public interface IRoleMenuService extends IService<RoleMenuEntity> {

    boolean updateRoleMenu(String roleId, List<String> menuIds);

    List<String> roleMenuIds(String roleId);

    boolean deleteRoleMenu(String roleId);

    List<String> roleResources(String roleId);


}
