package com.wms.admin.auth;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.UserEntity;
import com.wms.admin.entity.UserRoleEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.UserMapper;
import com.wms.admin.mapper.UserRoleMapper;
import com.wms.admin.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticServiceImpl implements AuthenticService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserInfo getUserInfo(String username, String password) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserEntity::getUserName, username);
        final UserEntity userEntity = userMapper.selectOne(queryWrapper);
        if (userEntity == null) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "用户");
        }
        if (checkEncrypt(userEntity, password)) {
            throw new BusinessException(ResultCode.USER_PWD_ERROR);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userEntity.getId());
        userInfo.setUsername(username);
        //目前支持一个角色，后期可以支持多个角色
        LambdaQueryWrapper<UserRoleEntity> roleQueryWrapper = new LambdaQueryWrapper<>();
        roleQueryWrapper
                .eq(UserRoleEntity::getDelFlag, WMSConstants.DEL_FLG_1)
                .eq(UserRoleEntity::getUserId, userEntity.getId());
        List<UserRoleEntity> roleList = userRoleMapper.selectList(roleQueryWrapper);
        final List<String> roleIds = roleList.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
        userInfo.setRoleIds(roleIds);
        return userInfo;
    }

    /**
     * check user password
     *
     * @param userEntity
     * @param password
     * @return
     */
    private boolean checkEncrypt(UserEntity userEntity, String password) {
        return Base64Util.encode(userEntity.getUserPwd()).equals(password);
    }
}
