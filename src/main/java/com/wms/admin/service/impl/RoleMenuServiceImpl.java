package com.wms.admin.service.impl;

import com.wms.admin.entity.RoleMenuEntity;
import com.wms.admin.mapper.RoleMenuMapper;
import com.wms.admin.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenuEntity> implements IRoleMenuService {

    @Override
    public boolean updateRoleMenu(String role, List<String> menuIds) {
        return false;
    }
}
