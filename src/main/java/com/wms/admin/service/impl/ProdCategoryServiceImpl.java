package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ProdCategoryEntity;
import com.wms.admin.entity.StoragesRegionEntity;
import com.wms.admin.mapper.ProdCategoryMapper;
import com.wms.admin.service.IProdCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.vo.ProdCategoryQueryVO;
import com.wms.admin.vo.ProdCategoryVO;
import com.wms.admin.vo.StoragesRegionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.wms.admin.commom.WMSConstants.DEL_FLG_1;

/**
 * <p>
 * 货物大类表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:21
 */
@Service
public class ProdCategoryServiceImpl extends ServiceImpl<ProdCategoryMapper, ProdCategoryEntity> implements IProdCategoryService {

    @Override
    public IPage<ProdCategoryVO> categoryPages(ProdCategoryQueryVO queryVO, PageParam pageParam) {
        IPage<ProdCategoryEntity> page = new Page<>(pageParam.getPage(), pageParam.getLimit());
        LambdaQueryWrapper<ProdCategoryEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProdCategoryEntity::getDelFlag, DEL_FLG_1);
        if (StringUtils.isNotBlank(queryVO.getName())) {
            queryWrapper.like(ProdCategoryEntity::getName, queryVO.getName());
        }
        queryWrapper.orderByDesc(ProdCategoryEntity::getCreateTime);
        IPage<ProdCategoryVO> resultPage = page(page, queryWrapper).convert(entity -> {
            ProdCategoryVO vo = new ProdCategoryVO();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        });
        return resultPage;
    }

    @Override
    public void addCategory(ProdCategoryVO vo) {

    }

    @Override
    public void updateCategory(ProdCategoryVO categoryVO) {

    }

    @Override
    public void deleteCategory(String regionId) {

    }
}
