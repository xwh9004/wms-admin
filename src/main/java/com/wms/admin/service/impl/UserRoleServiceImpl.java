package com.wms.admin.service.impl;

import com.wms.admin.entity.UserRoleEntity;
import com.wms.admin.mapper.UserRoleMapper;
import com.wms.admin.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-22 12:41:09
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements IUserRoleService {

}
