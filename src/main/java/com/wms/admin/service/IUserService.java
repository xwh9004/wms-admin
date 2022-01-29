package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.UserEntity;
import com.wms.admin.vo.UserQueryVO;
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

    IPage<UserVO> userPage(UserQueryVO userQueryVO, PageParam pageParam);

    boolean addUser(UserVO userVO);

    boolean updateUser(UserVO userVO);

    boolean deleteUser(String userId);
}
