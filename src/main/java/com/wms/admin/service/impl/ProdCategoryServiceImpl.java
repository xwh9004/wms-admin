package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.MeasurementUnitEntity;
import com.wms.admin.entity.ProdCategoryEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.MeasurementUnitMapper;
import com.wms.admin.mapper.ProdCategoryMapper;
import com.wms.admin.service.IProdCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.ProdCategoryQueryVO;
import com.wms.admin.vo.ProdCategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.wms.admin.commom.WMSConstants.DEL_FLG_N;

/**
 * <p>
 * 货物大类表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:21
 */
@Slf4j
@Service
public class ProdCategoryServiceImpl extends ServiceImpl<ProdCategoryMapper, ProdCategoryEntity> implements IProdCategoryService {

    @Autowired
    private ProdCategoryMapper prodCategoryMapper;

    @Autowired
    private MeasurementUnitMapper measurementUnitMapper;

    @Override
    public List<ProdCategoryVO> categoryAll() {
        LambdaQueryWrapper<ProdCategoryEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProdCategoryEntity::getDelFlag, DEL_FLG_N);

        queryWrapper.orderByDesc(ProdCategoryEntity::getCreateTime);
        List<ProdCategoryVO> list = new ArrayList<>();
        list(queryWrapper).forEach(entity -> {
            ProdCategoryVO vo = new ProdCategoryVO();
            BeanUtils.copyProperties(entity, vo);
            list.add(vo);
        });
        return list;
    }

    @Override
    public IPage<ProdCategoryVO> categoryPages(ProdCategoryQueryVO queryVO, PageParam pageParam) {
        IPage<ProdCategoryEntity> page = new Page<>(pageParam.getPage(), pageParam.getLimit());
        LambdaQueryWrapper<ProdCategoryEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProdCategoryEntity::getDelFlag, DEL_FLG_N);
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
    public boolean addCategory(ProdCategoryVO vo) {
        checkForAdd(vo);
        ProdCategoryEntity entity = new ProdCategoryEntity();
        BeanUtils.copyProperties(vo, entity);
        return save(entity);
    }

    private void checkForAdd(ProdCategoryVO vo) {
        List<ProdCategoryEntity> categories = queryByCode(vo.getCode());
        if (!categories.isEmpty()) {
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "大类" + vo.getCode());
        }
        checkUnit(vo);
    }

    private void checkUnit(ProdCategoryVO vo) {
        Integer unitId = vo.getUnitId();
        MeasurementUnitEntity unit = measurementUnitMapper.selectById(unitId);
        if(Objects.isNull(unit)){
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "自然单位" + vo.getUnitId());
        }
        if(StringUtils.equals(unit.getDelFlag(),WMSConstants.DEL_FLG_Y)){
            throw new BusinessException(ResultCode.COMMON_ERROR, "自然单位不可用");
        }
        Integer chargeUnitId = vo.getChargeUnitId();
        MeasurementUnitEntity chargeUnit = measurementUnitMapper.selectById(chargeUnitId);
        if(Objects.isNull(chargeUnit)){
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "计费单位" +vo.getChargeUnitId());
        }
        if(StringUtils.equals(unit.getDelFlag(),WMSConstants.DEL_FLG_Y)){
            throw new BusinessException(ResultCode.COMMON_ERROR, "计费单位不可用");
        }
    }

    private List<ProdCategoryEntity> queryByCode(String code) {
        QueryWrapper<ProdCategoryEntity> cond = new QueryWrapper<>();
        cond.lambda().eq(ProdCategoryEntity::getDelFlag, DEL_FLG_N).eq(ProdCategoryEntity::getCode, code);
        return prodCategoryMapper.selectList(cond);
    }

    @Override
    public boolean updateCategory(ProdCategoryVO categoryVO) {
        checkForUpdate(categoryVO);
        ProdCategoryEntity entity = prodCategoryMapper.selectById(categoryVO.getId());
        if (entity == null || WMSConstants.DEL_FLG_Y.equals(entity.getDelFlag())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, String.format("大类%s",categoryVO.getId()));
        }
        BeanUtils.copyProperties(categoryVO, entity);
        return updateById(entity);
    }

    private void checkForUpdate(ProdCategoryVO categoryVO) {
        if (Objects.nonNull(categoryVO.getId())) {
            throw new BusinessException(ResultCode.PARAM_NOT_NULL, "ID");
        }
        if (categoryVO.getId().intValue()<1) {
            throw new BusinessException(ResultCode.PARAM_ERROR, "ID不能小于1");
        }
        List<ProdCategoryEntity> existCategories = queryByCode(categoryVO.getCode());
        if (existCategories.isEmpty()) {
            return;
        }
        if (existCategories.size() == 1) {
            ProdCategoryEntity category = existCategories.get(0);
            if (category.getId().equals(categoryVO.getId())) {
                return;
            }
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "产品大类" + category.getCode());
        }
        if (existCategories.size() > 1) {
            log.error("存在多条[{}条] 产品大类编号{}的记录", existCategories.size(), categoryVO.getCode());
            throw new BusinessException(ResultCode.DATA_ERROR, "产品大类数据异常");
        }
        checkUnit(categoryVO);
    }

    @Override
    public boolean deleteCategory(String id) {
        Assert.notNull(id, "ID不能为空");
        ProdCategoryEntity entity = prodCategoryMapper.selectById(id);
        if (entity == null || WMSConstants.DEL_FLG_Y.equals(entity.getDelFlag())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "大类");
        }
        entity.setDelFlag(WMSConstants.DEL_FLG_Y);
        return updateById(entity);
    }
}
