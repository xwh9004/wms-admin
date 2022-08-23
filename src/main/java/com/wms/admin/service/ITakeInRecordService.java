package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.TakeInRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.TakeInQueryVO;
import com.wms.admin.vo.TakeInVO;

/**
 * <p>
 * 收货记录表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
public interface ITakeInRecordService extends IService<TakeInRecordEntity> {

    IPage<TakeInVO> takeInList(TakeInQueryVO queryVO, PageParam pageParam);

    void takeInAdd(TakeInVO takeInVO);

    TakeInVO detail(Integer id);

    void takeInUpdate(TakeInVO takeInVO);

    void takenIn(Integer id);
}
