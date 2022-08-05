package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.MeasurementUnitEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.MeasurementUnitMapper;
import com.wms.admin.service.IMeasurementUnitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.VOUtil;
import com.wms.admin.vo.MeasurementUnitVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 计量单位表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
@Service
public class MeasurementUnitServiceImpl extends ServiceImpl<MeasurementUnitMapper, MeasurementUnitEntity> implements IMeasurementUnitService {


    @Override
    public IPage<MeasurementUnitVO> selectList(MeasurementUnitVO vo, PageParam pageParam) {

        QueryWrapper<MeasurementUnitEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(vo.getUnitName())) {
            queryWrapper.lambda().like(MeasurementUnitEntity::getUnitName,
                    vo.getUnitName());
        }
        if (StringUtils.isNotBlank(vo.getUnitSymbol())) {
            queryWrapper.lambda().like(MeasurementUnitEntity::getUnitSymbol
                    , vo.getUnitSymbol());
        }
        queryWrapper.lambda().orderByDesc(MeasurementUnitEntity::getUpdateTime);
        IPage<MeasurementUnitEntity> page = Page.of(pageParam.getPage(), pageParam.getLimit());
        page = baseMapper.selectPage(page, queryWrapper);
        IPage<MeasurementUnitVO> result = page.convert(entity -> {
            MeasurementUnitVO unitVO = new MeasurementUnitVO();
            BeanUtils.copyProperties(entity, unitVO);
            return unitVO;
        });
        return result;

    }
    @Transactional
    @Override
    public void addUnit(MeasurementUnitVO vo) {
        checkForAdd(vo);
        MeasurementUnitEntity measurementUnitEntity = VOUtil.convertTo(vo, v -> {
            MeasurementUnitEntity entity = new MeasurementUnitEntity();
            BeanUtils.copyProperties(vo, entity);
            return entity;
        });
        save(measurementUnitEntity);
    }

    @Transactional
    @Override
    public void updateUnit(MeasurementUnitVO vo) {
        MeasurementUnitEntity entity = checkForUpdate(vo);
        entity.setUnitName(vo.getUnitName());
        entity.setUnitSymbol(vo.getUnitSymbol());
        updateById(entity);

    }

    private MeasurementUnitEntity checkForUpdate(MeasurementUnitVO vo) {
        if (Objects.isNull(vo.getId())) {
            throw new BusinessException(ResultCode.PARAM_NOT_NULL, "符号ID");
        }
        MeasurementUnitEntity entity = getById(vo.getId());
        if (Objects.isNull(entity)) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "数据");
        }
        if (StringUtils.equals(WMSConstants.DEL_FLG_0, entity.getDelFlag())) {
            throw new BusinessException(ResultCode.PARAM_ERROR, "数据已删除");
        }
        QueryWrapper<MeasurementUnitEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                .eq(MeasurementUnitEntity::getUnitName, vo.getUnitName())
                .ne(MeasurementUnitEntity::getId, vo.getId());
        MeasurementUnitEntity other = getOne(queryWrapper);
        if (Objects.nonNull(other)) {
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "单位" + vo.getUnitName());
        }
        return entity;
    }

    @Override
    public MeasurementUnitVO queryByName(String unitName) {
        MeasurementUnitEntity unitEntity = baseMapper.queryUnitByName(unitName);
        MeasurementUnitVO vo = new MeasurementUnitVO();
        BeanUtils.copyProperties(unitEntity, vo);
        return vo;
    }

    @Override
    public List<MeasurementUnitVO> selectAll() {
        QueryWrapper<MeasurementUnitEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MeasurementUnitEntity::getDelFlag, WMSConstants.DEL_FLG_0);
        List<MeasurementUnitEntity> entities = list(queryWrapper);
        List<MeasurementUnitVO> list = entities.stream().map(e -> {
            MeasurementUnitVO vo = new MeasurementUnitVO();
            BeanUtils.copyProperties(e, vo);
            return vo;
        }).collect(Collectors.toList());
        return list;
    }

    private void checkForAdd(MeasurementUnitVO vo) {
        //单位是否存在
        String name = vo.getUnitName();
        MeasurementUnitEntity unitEntity = baseMapper.queryUnitByName(name);
        if (Objects.nonNull(unitEntity)) {
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, String.format("单位%s", name));
        }
    }
    @Transactional
    @Override
    public void deleteBy(String id) {
        //TODO 要校验是否有使用了该符号的物品
        MeasurementUnitEntity entity = getById(id);
        if (entity == null) {
            return;
        }
        String name = entity.getUnitName();
        if (deletable(name)) {
            baseMapper.deleteById(id);
            return;
        }
        throw new BusinessException(ResultCode.COMMON_ERROR, "该单位被使用，不能删除");
    }

    private boolean deletable(String name) {
        return true;
    }
}
