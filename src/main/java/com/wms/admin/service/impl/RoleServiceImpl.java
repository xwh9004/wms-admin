package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.RoleEntity;
import com.wms.admin.entity.RoleMenuEntity;
import com.wms.admin.mapper.RoleMapper;
import com.wms.admin.mapper.RoleMenuMapper;
import com.wms.admin.service.IMenuService;
import com.wms.admin.service.IRoleMenuService;
import com.wms.admin.service.IRoleService;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.MenuVO;
import com.wms.admin.vo.RoleQueryVO;
import com.wms.admin.vo.RoleVO;
import com.wms.admin.vo.RouteVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.wms.admin.commom.WMSConstants.DEL_FLG_0;
import static com.wms.admin.commom.WMSConstants.DEL_FLG_1;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements IRoleService {

    @Autowired
    private IRoleMenuService roleMenuService;

    @Autowired
    private IMenuService menuService;

    @Transactional
    @Override
    public boolean addRole(RoleVO roleVO) {
        checkRole(roleVO);
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleVO, roleEntity);
        String roleId = UUIDUtil.uuid();
        roleEntity.setId(roleId);
        roleEntity.setCreateBy(UserInfoContext.getUsername());
        roleEntity.setUpdateBy(UserInfoContext.getUsername());
        roleVO.setId(roleId);
        saveRoleMenu(roleVO);
        return save(roleEntity);
    }

    private void checkRole(RoleVO roleVO) {
    }

    @Transactional
    @Override
    public boolean updateRole(RoleVO roleVO) {
        checkRoleForUpdate(roleVO);
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleVO, roleEntity);
        roleEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(roleEntity);
        return saveRoleMenu(roleVO);
    }

    private boolean saveRoleMenu(RoleVO roleVO) {
        return roleMenuService.updateRoleMenu(roleVO.getId(), roleVO.getMenuIds());
    }

    private void checkRoleForUpdate(RoleVO roleVO) {
    }

    @Override
    public boolean deleteRole(String roleId) {
        LambdaUpdateWrapper<RoleEntity> updateWrapper = new LambdaUpdateWrapper();
        updateWrapper.eq(RoleEntity::getId, roleId).set(RoleEntity::getDelFlag, DEL_FLG_0);
        update(updateWrapper);
        return roleMenuService.deleteRoleMenu(roleId);
    }

    @Override
    public List<RoleVO> roleList() {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleEntity::getDelFlag, DEL_FLG_1);
        List<RoleEntity> list = list(queryWrapper);
        List<RoleVO> roleList = list.stream().map(item -> {
            RoleVO roleVO = new RoleVO();
            BeanUtils.copyProperties(item, roleVO);
            return roleVO;
        }).collect(Collectors.toList());
        return roleList;
    }

    @Override
    public IPage<RoleVO> rolePage(RoleQueryVO roleQueryVO, PageParam pageParam) {
        IPage<RoleEntity> page = new Page<>(pageParam.getPage(), pageParam.getLimit());
        LambdaQueryWrapper<RoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleEntity::getDelFlag, DEL_FLG_1);
        if (StringUtils.isNotBlank(roleQueryVO.getRoleName())) {
            queryWrapper.like(RoleEntity::getRoleName, roleQueryVO.getRoleName());
        }
        if (StringUtils.isNotBlank(roleQueryVO.getRoleCode())) {
            queryWrapper.like(RoleEntity::getRoleCode, roleQueryVO.getRoleCode());
        }
        if (StringUtils.isNotBlank(roleQueryVO.getType())) {
            queryWrapper.eq(RoleEntity::getType, roleQueryVO.getType());
        }
        queryWrapper.orderByDesc(RoleEntity::getCreateTime);
        page = page(page, queryWrapper);
        IPage<RoleVO> resultPage = page.convert(entity -> {
            RoleVO roleVO = new RoleVO();
            BeanUtils.copyProperties(entity, roleVO);
            return roleVO;
        });
        return resultPage;
    }

    @Override
    public List<String> rolePermission(String roleId) {
        return  roleMenuService.roleMenuIds(roleId);
    }

    @Override
    public List<MenuVO> allPermission() {
        return menuService.queryList();
    }

    @Override
    public List<String> roleResources(String roleId) {
        return roleMenuService.roleResources(roleId);
    }
}
