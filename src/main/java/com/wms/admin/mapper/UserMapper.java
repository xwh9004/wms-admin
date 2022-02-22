package com.wms.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.auth.UserInfo;
import com.wms.admin.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.UserQueryVO;
import com.wms.admin.vo.UserVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    IPage<UserVO> userPage(@Param("param") UserQueryVO userQueryVO, Page page);

}
