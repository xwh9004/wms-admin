package com.wms.admin.service.impl;

import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.entity.MeasurementUnitEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.MeasurementUnitMapper;
import com.wms.admin.service.IMeasurementUnitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.VOUtil;
import com.wms.admin.vo.MeasurementUnitVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
    public void selectList(MeasurementUnitVO vo, PageParam pageParam) {

    }

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

    @Override
    public void updateUnit(MeasurementUnitVO vo) {

    }

    @Override
    public MeasurementUnitVO queryUnitSymbol(String symbol) {
        MeasurementUnitEntity unitEntity = baseMapper.queryUnitSymbol(symbol);
        MeasurementUnitVO vo = new MeasurementUnitVO();
        BeanUtils.copyProperties(unitEntity,vo);
        return vo;
    }

    private void checkForAdd(MeasurementUnitVO vo) {
        //单位是否存在
        String symbol =vo.getUnitSymbol();
        MeasurementUnitEntity unitEntity = baseMapper.queryUnitSymbol(symbol);
        if(Objects.nonNull(unitEntity)){
            throw new BusinessException(ResultCode.RESOURCE_EXISTS,String.format("符号%s",symbol));
        }
    }

}
