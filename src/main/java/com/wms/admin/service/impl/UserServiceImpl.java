package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.entity.UserEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.UserMapper;
import com.wms.admin.service.IUserService;
import com.wms.admin.util.Base64Util;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.UserQueryVO;
import com.wms.admin.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return userVO;
    }

    @Override
    public IPage<UserVO> userPage(UserQueryVO userQueryVO, PageParam pageParam) {
        IPage<UserEntity> page = new Page<>(pageParam.getPage(),pageParam.getLimit());
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getDelFlag,DEL_FLG_1);
        if (StringUtils.isNotBlank(userQueryVO.getUsername())) {
            queryWrapper.like(UserEntity::getUserName, userQueryVO.getUsername());
        }
        if (StringUtils.isNotBlank(userQueryVO.getCardNo())) {
            queryWrapper.like(UserEntity::getCardNo, userQueryVO.getCardNo());
        }
        if (StringUtils.isNotBlank(userQueryVO.getDepartment())) {
            queryWrapper.like(UserEntity::getDeptName, userQueryVO.getDepartment());
        }
        page=page(page,queryWrapper);
        IPage<UserVO> resultPage = page.convert(entity -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(entity,userVO);
            return userVO;
        });
        return resultPage;
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
        String pwd = userVO.getPassword();
        userEntity.setUserPwd(Base64Util.encode(pwd));
        userEntity.setCreateBy(UserInfoContext.getUsername());
        userEntity.setUpdateBy(UserInfoContext.getUsername());
        return save(userEntity);
    }

    @Transactional
    @Override
    public boolean updateUser(UserVO userVO) {
        checkUserForUpdate(userVO);
        UserEntity userEntity = getById(userVO.getId());
        BeanUtils.copyProperties(userVO,userEntity,"id");
        return  updateById(userEntity);
    }

    private void checkUserForUpdate(UserVO userVO) {

    }

    @Transactional
    @Override
    public boolean deleteUser(String userId) {
        checkUserForDelete(userId);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setDelFlag(DEL_FLG_0);
        return  updateById(userEntity);
    }

    private void checkUserForDelete(String userId) {

    }
}
