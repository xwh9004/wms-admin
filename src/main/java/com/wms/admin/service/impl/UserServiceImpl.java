package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.dto.UserDto;
import com.wms.admin.entity.UserEntity;
import com.wms.admin.entity.UserRoleEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.UserMapper;
import com.wms.admin.mapper.UserRoleMapper;
import com.wms.admin.service.IUserService;
import com.wms.admin.util.Base64Util;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.UserQueryVO;
import com.wms.admin.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Objects;

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
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO selectUser(String userId) {
        final UserEntity userEntity = getById(userId);
        checkUserStatus(userEntity);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userEntity, userVO);
        return userVO;
    }

    @Override
    public IPage<UserVO> userPage(UserQueryVO userQueryVO, PageParam pageParam) {
        Page page = new Page<>(pageParam.getPage(), pageParam.getLimit());
        IPage<UserVO> resultPage = userMapper.userPage(userQueryVO, page);
        return resultPage;
    }

    private void checkUserStatus(UserEntity userEntity) {
        if (DEL_FLG_0.equals(userEntity.getDelFlag())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "用户");
        }
    }

    @Override
    public boolean addUser(UserDto userDto) {
        checkForAdd(userDto);
        final UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setId(UUIDUtil.uuid());
        userEntity.setUserPwd(Base64Util.encode(userDto.getPassword()));
        save(userEntity);
        saveUserRole(userEntity.getId(),userDto.getRoleId());
        return true;
    }

    private void checkForAdd(UserDto userDto) {
        checkUserNameExisted(userDto.getUserName());
    }

    private void checkUserNameExisted(String userName) {
        QueryWrapper<UserEntity> cond = new QueryWrapper<>();
        cond.lambda().eq(UserEntity::getUserName,userName);
        UserEntity user = getOne(cond);
        if(Objects.nonNull(user)){
            throw new BusinessException(ResultCode.RESOURCE_EXISTS,"用户".concat(userName));
        }
    }

    private void saveUserRole(String userId, String roleId) {
        Assert.notNull(roleId, "角色不能为空");
        //check roleId;
        UserRoleEntity role = userRoleMapper.selectById(roleId);
        if (role == null) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "角色".concat(roleId));
        }
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userId);
        userRoleEntity.setRoleId(roleId);
        userRoleMapper.insert(userRoleEntity);
    }

    @Transactional
    @Override
    public boolean updateUser(UserDto userDto) {
        checkUserForUpdate(userDto);
        UserEntity userEntity = getById(userDto.getId());
        BeanUtils.copyProperties(userDto, userEntity);
        updateById(userEntity);
        updateUserRole(userDto.getId(),userDto.getRoleId());
        return true;
    }

    private void updateUserRole(String userId,String roleId) {
        LambdaUpdateWrapper<UserRoleEntity> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(UserRoleEntity::getUserId, userId);
        final UserRoleEntity userRoleEntity = userRoleMapper.selectOne(queryWrapper);
        if (userRoleEntity == null) {
            throw new BusinessException(ResultCode.SYS_ERROR, "用户选择的角色不存在");
        }
        userRoleEntity.setUpdateBy(UserInfoContext.getUsername());
        userRoleEntity.setRoleId(roleId);
        userRoleMapper.updateById(userRoleEntity);
    }

    private void checkUserForUpdate(UserDto userDto) {

    }

    @Transactional
    @Override
    public boolean deleteUser(String userId) {
        checkUserForDelete(userId);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setDelFlag(DEL_FLG_0);
        return updateById(userEntity);
    }

    private void checkUserForDelete(String userId) {

    }
}
