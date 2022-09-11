package com.wms.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.wms.admin.BaseTest;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.entity.ProdCategoryEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.ProdCategoryMapper;
import com.wms.admin.service.impl.ProdCategoryServiceImpl;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.ProdCategoryVO;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
public class ProdCategoryServiceTest extends BaseTest {

    @InjectMocks
    private ProdCategoryServiceImpl categoryService;

    @Mock
    private ProdCategoryMapper prodCategoryMapper;


    @Before
    public void before() {
        super.before();
        Mockito.when(prodCategoryMapper.insert(Mockito.any(ProdCategoryEntity.class))).thenReturn(1);

    }

    @Test
    public void add_success_test() {
        ProdCategoryVO vo = new ProdCategoryVO();
        boolean result = categoryService.addCategory(vo);
        Assert.assertTrue(result);
    }

    @Test(expected = BusinessException.class)
    public void add_exist_code_test() {
        ProdCategoryVO vo = new ProdCategoryVO();
        vo.setCode("test");
        Mockito.when(prodCategoryMapper.selectList(Mockito.any(Wrapper.class))).thenReturn(existList());
        categoryService.addCategory(vo);
    }
    @Test(expected = BusinessException.class)
    public void update_not_exist_code_test() {
        ProdCategoryVO vo = new ProdCategoryVO();
        vo.setId(1);
        vo.setCode("test");
        Mockito.when(prodCategoryMapper.selectList(Mockito.any(Wrapper.class))).thenReturn(existList());
        categoryService.updateCategory(vo);
    }

    @Test(expected = BusinessException.class)
    public void delete_test() {
        ProdCategoryVO vo = new ProdCategoryVO();
        vo.setId(1);
        vo.setCode("test");
        Mockito.when(prodCategoryMapper.selectById(Mockito.any())).thenReturn(null);
        categoryService.deleteCategory(UUIDUtil.uuid());
    }
    private ProdCategoryEntity existOne() {
        ProdCategoryEntity entity = new ProdCategoryEntity();
        entity.setCode("test");
        return entity;
    }

    private List<ProdCategoryEntity> existList() {
        List<ProdCategoryEntity> list = new ArrayList<>();
        ProdCategoryEntity entity = new ProdCategoryEntity();
        entity.setId(UUIDUtil.uuid());
        entity.setCode("test");
        list.add(entity);
        return list;
    }
}
