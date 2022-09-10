package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.TakeOutRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.TakeInVO;
import com.wms.admin.vo.TakeOutQueryVO;
import com.wms.admin.vo.TakeOutVO;

/**
 * <p>
 * 发货记录表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
public interface ITakeOutRecordService extends IService<TakeOutRecordEntity> {

    IPage<TakeOutVO> takeOutList(TakeOutQueryVO queryVO, PageParam pageParam);

    void takeOutAdd(TakeOutVO takeOutVO);

    void takeOutUpdate(TakeOutVO takeOutVO);

    TakeOutVO detail(Integer id);

    void takenOut(Integer id);
}
