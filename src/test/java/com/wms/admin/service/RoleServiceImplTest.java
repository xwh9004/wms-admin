package com.wms.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.wms.admin.BaseTest;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.entity.ProdCategoryEntity;
import com.wms.admin.entity.RoleEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.RoleMapper;
import com.wms.admin.service.impl.RoleServiceImpl;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.RoleVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class RoleServiceImplTest extends BaseTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleMapper roleMapper;

    @Before
    public void before() {
        PowerMockito.mockStatic(UserInfoContext.class);
        Mockito.when(roleMapper.insert(Mockito.any(RoleEntity.class))).thenReturn(1);
        Mockito.when(roleMapper.selectOne(Mockito.any(Wrapper.class))).thenReturn(existOne());
        Mockito.when(roleMapper.selectById(Mockito.any(String.class))).thenReturn(existOne());

    }
    private RoleEntity existOne() {
        RoleEntity entity = new RoleEntity();
        entity.setId(UUIDUtil.uuid());
        entity.setRoleCode("test");
        return entity;
    }

    @Test(expected = BusinessException.class)
    public void addRole(){
        RoleVO vo = new RoleVO();
        vo.setRoleCode("test");
        vo.setType("1");
        roleService.addRole(vo);
    }

    @Test(expected = BusinessException.class)
    public void updateRole(){
        RoleVO vo = new RoleVO();
        vo.setRoleCode("test");
        vo.setType("1");
        vo.setId(UUIDUtil.uuid());
        roleService.updateRole(vo);
    }
}
