package com.wms.admin.service;

import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.MeasurementUnitEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.MeasurementUnitVO;

/**
 * <p>
 * 计量单位表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
public interface IMeasurementUnitService extends IService<MeasurementUnitEntity> {
    /**
     * 查询单位
     * @param vo
     * @param pageParam
     */
    void selectList(MeasurementUnitVO vo, PageParam pageParam);
    /**
     * 新增计量单位
     * @param vo
     */
    void addUnit(MeasurementUnitVO vo);

    /**
     * 修改单位
     * @param vo
     */
    void updateUnit(MeasurementUnitVO vo);

    /**
     * 根据符号查询 计量单位
     * @param symbol
     * @return
     */
    MeasurementUnitVO queryUnitSymbol(String symbol);
}
