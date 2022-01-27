package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.PageQuery;
import com.wms.admin.entity.RoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.RoleVO;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
public interface IRoleService extends IService<RoleEntity> {

    boolean addRole(RoleVO roleVO);

    boolean updateRole(RoleVO roleVO);

    boolean deleteRole(RoleVO roleVO);

    List<RoleVO>  roleList();

    IPage<RoleVO> rolePage(PageQuery pageQuery);

}
