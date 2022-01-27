package com.wms.admin.service.impl;

import com.wms.admin.commom.ResultCode;
import com.wms.admin.entity.UserEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.UserMapper;
import com.wms.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.Base64Util;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.UserVO;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    private static final String DEL_FLG_1 = "1";
    private static final String DEL_FLG_0 = "0";

    @Override
    public UserVO selectUser(String userId) {
        final UserEntity userEntity = getById(userId);
        checkUserStatus(userEntity);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userEntity, userVO);
        return null;
    }

    private void checkUserStatus(UserEntity userEntity) {
        if (DEL_FLG_0.equals(userEntity.getDelFlag())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "用户");
        }
    }

    @Override
    public boolean addUser(UserVO userVO) {
        final UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userVO,userEntity);
        userEntity.setId(UUIDUtil.uuid());
        String pwd = userVO.getUserPwd();
        userEntity.setUserPwd(Base64Util.encode(pwd));
        userEntity.setCreateBy("sys");
        userEntity.setUpdateBy("sys");
        return save(userEntity);
    }

    @Override
    public boolean updateUser(UserVO userVO) {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        return false;
    }
}
