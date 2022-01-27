package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageQuery;
import com.wms.admin.entity.RoleEntity;
import com.wms.admin.mapper.RoleMapper;
import com.wms.admin.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.vo.RoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public boolean addRole(RoleVO roleVO) {
        checkRole(roleVO);
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleVO,roleEntity);

        return false;
    }

    private void checkRole(RoleVO roleVO) {
    }

    @Override
    public boolean updateRole(RoleVO roleVO) {
        return false;
    }

    @Override
    public boolean deleteRole(RoleVO roleVO) {
        return false;
    }

    @Override
    public List<RoleVO> roleList() {
        return null;
    }

    @Override
    public IPage<RoleVO> rolePage(PageQuery pageQuery) {
        return null;
    }
}
