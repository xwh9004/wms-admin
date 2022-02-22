package com.wms.admin.mapper;

import com.wms.admin.entity.UserRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.UserRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-02-22 12:41:09
 */
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

    List<UserRoleVO> selectUserRoles(@Param("userId") String id);
}
