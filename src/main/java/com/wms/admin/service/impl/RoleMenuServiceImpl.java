package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.RoleMenuEntity;
import com.wms.admin.mapper.RoleMenuMapper;
import com.wms.admin.service.IMenuService;
import com.wms.admin.service.IRoleMenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.wms.admin.commom.WMSConstants.DEL_FLG_N;

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

    @Autowired
    private IMenuService menuService;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Transactional
    @Override
    public boolean updateRoleMenu(String roleId, List<String> menuIds) {
        List<String> newMenuIds = menuIds;
        List<String> previousMenuIds = roleMenuIds(roleId);
        if (CollectionUtils.isEmpty(previousMenuIds)) {
            insertRoleMenu(roleId, menuIds);
            return true;
        }
        //没有修改的角色菜单
        Collection<String> intersection = CollectionUtils.intersection(previousMenuIds, newMenuIds);
        Collection<String> insertCollections = CollectionUtils.subtract(newMenuIds, intersection);
        Collection<String> deleteCollections = CollectionUtils.subtract(previousMenuIds, intersection);
        deleteRoleMenu(roleId, deleteCollections);
        insertRoleMenu(roleId, insertCollections);
        return true;
    }

    private void insertRoleMenu(String roleId, Collection<String> menuIds) {
        if (menuIds.isEmpty()) {
            return;
        }
        Collection<RoleMenuEntity> roleMenus = new ArrayList<>();
        menuIds.forEach(menuId -> {
            RoleMenuEntity roleMenu = new RoleMenuEntity();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.setCreateBy(UserInfoContext.getUsername());
            roleMenu.setUpdateBy(UserInfoContext.getUsername());
            roleMenus.add(roleMenu);
        });
        saveBatch(roleMenus);
    }

    private void deleteRoleMenu(String roleId, Collection<String> menuIds) {
        if (menuIds.isEmpty()) {
            return;
        }
        LambdaUpdateWrapper<RoleMenuEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(RoleMenuEntity::getRoleId, roleId)
                .in(RoleMenuEntity::getMenuId, menuIds).set(RoleMenuEntity::getDelFlag, WMSConstants.DEL_FLG_Y);
        update(updateWrapper);
    }

    @Override
    public List<String> roleMenuIds(String roleId) {
        LambdaQueryWrapper<RoleMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenuEntity::getRoleId, roleId).eq(RoleMenuEntity::getDelFlag, DEL_FLG_N);
        List<RoleMenuEntity> roleMenus = list(queryWrapper);
        List<String> menuIds = roleMenus.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
        return menuIds;
    }

    @Override
    public boolean deleteRoleMenu(String roleId) {
        LambdaUpdateWrapper<RoleMenuEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(RoleMenuEntity::getRoleId, roleId)
                .set(RoleMenuEntity::getDelFlag, WMSConstants.DEL_FLG_Y);
        return update(updateWrapper);
    }

    @Override
    public List<String> roleResources(String roleId) {
        return roleMenuMapper.roleResources(roleId);
    }

}
