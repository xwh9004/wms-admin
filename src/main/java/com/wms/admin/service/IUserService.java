package com.wms.admin.service;

import com.wms.admin.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.UserVO;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
public interface IUserService extends IService<UserEntity> {

    UserVO selectUser(String userId);

    boolean addUser(UserVO userVO);

    boolean updateUser(UserVO userVO);

    boolean deleteUser(String userId);
}
