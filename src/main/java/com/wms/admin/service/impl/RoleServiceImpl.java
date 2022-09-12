package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.PermissionResource;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.entity.RoleEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.RoleMapper;
import com.wms.admin.service.IMenuService;
import com.wms.admin.service.IRoleMenuService;
import com.wms.admin.service.IRoleService;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.MenuVO;
import com.wms.admin.vo.RoleQueryVO;
import com.wms.admin.vo.RoleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

import static com.wms.admin.commom.WMSConstants.DEL_FLG_Y;
import static com.wms.admin.commom.WMSConstants.DEL_FLG_N;

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
    /**
     * 管理员用户
     */
    private String TYPE_ADMIN = "1";
    /**
     * 普通用户
     */
    private String TYPE_NORMAL = "2";

    @Transactional
    @Override
    public boolean addRole(RoleVO roleVO) {
        checkRoleForAdd(roleVO);
        String roleId = UUIDUtil.uuid();
        roleVO.setId(roleId);
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleVO, roleEntity);
        saveRoleMenu(roleVO);
        return save(roleEntity);
    }

    private void checkRoleForAdd(RoleVO roleVO) {
        checkType(roleVO.getType());
        //roleCode 唯一
        String roleCode = roleVO.getRoleCode();
        RoleEntity role = getRoleByCode(roleCode);
        if (Objects.nonNull(role)) {
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "角色"+roleCode);
        }
    }

    private RoleEntity getRoleByCode(String roleCode) {
        QueryWrapper<RoleEntity> cond = new QueryWrapper<>();
        cond.lambda().eq(RoleEntity::getDelFlag, DEL_FLG_N).eq(RoleEntity::getRoleCode, roleCode);
        return getOne(cond);
    }

    private void checkType(String type) {
        if (!TYPE_ADMIN.equals(type) && !TYPE_NORMAL.equals(type)) {
            throw new BusinessException(ResultCode.PARAM_ERROR, "角色类型");
        }
    }

    @Transactional
    @Override
    public boolean updateRole(RoleVO roleVO) {
        checkRoleForUpdate(roleVO);
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleVO, roleEntity);
        updateById(roleEntity);
        return saveRoleMenu(roleVO);
    }

    private boolean saveRoleMenu(RoleVO roleVO) {
        return roleMenuService.updateRoleMenu(roleVO.getId(), roleVO.getMenuIds());
    }

    private void checkRoleForUpdate(RoleVO roleVO) {
        checkExistRole(roleVO.getId());
        QueryWrapper<RoleEntity> cond = new QueryWrapper<>();
        cond.lambda()
                .eq(RoleEntity::getDelFlag, DEL_FLG_N)
                .eq(RoleEntity::getRoleCode, roleVO.getRoleCode())
                .ne(RoleEntity::getId,roleVO.getId());
        RoleEntity roleByCode = getOne(cond);
        if(Objects.nonNull(roleByCode)){
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "角色"+roleVO.getRoleCode());
        }
    }

    @Override
    public boolean deleteRole(String roleId) {
        checkRoleForDel(roleId);
        LambdaUpdateWrapper<RoleEntity> updateWrapper = new LambdaUpdateWrapper();
        updateWrapper.eq(RoleEntity::getId, roleId).set(RoleEntity::getDelFlag, DEL_FLG_Y);
        update(updateWrapper);
        return roleMenuService.deleteRoleMenu(roleId);
    }

    private void checkExistRole(String roleId){
        Assert.notNull(roleId,"ID不能为空");
        RoleEntity role = getRoleEntity(roleId);
        if(Objects.isNull(role)){
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "角色");
        }
    }

    private void checkRoleForDel(String roleId) {
        checkExistRole(roleId);
    }

    private RoleEntity getRoleEntity(String roleId) {
        QueryWrapper<RoleEntity> cond = new QueryWrapper<>();
        cond.lambda()
                .eq(RoleEntity::getDelFlag, DEL_FLG_N)
                .eq(RoleEntity::getId, roleId);
        RoleEntity role = getOne(cond);
        return role;
    }

    @Override
    public List<RoleVO> roleList() {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleEntity::getDelFlag, DEL_FLG_N);
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
        queryWrapper.eq(RoleEntity::getDelFlag, DEL_FLG_N);
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
        return roleMenuService.roleMenuIds(roleId);
    }

    @Override
    public List<MenuVO> allPermission() {
        return menuService.queryList();
    }

    @Override
    public List<String> roleResources(String roleId) {
        return roleMenuService.roleResources(roleId);
    }

    @Override
    public List<PermissionResource> rolePermissions(String roleId) {
        return roleMenuService.rolePermissions(roleId);
    }
}
