package com.wms.admin.service;

import com.wms.admin.entity.RoleMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;

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

    boolean updateRoleMenu(String role, List<String> menuIds);


}
