package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.TakeInRecordEntity;
import com.wms.admin.mapper.TakeInRecordMapper;
import com.wms.admin.service.ITakeInRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.vo.TakeInQueryVO;
import com.wms.admin.vo.TakeInVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收货记录表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
@Service
public class TakeInRecordServiceImpl extends ServiceImpl<TakeInRecordMapper, TakeInRecordEntity> implements ITakeInRecordService {

    @Override
    public IPage<TakeInVO> takeInList(TakeInQueryVO queryVO, PageParam pageParam) {
        return null;
    }

    @Override
    public void takeInAdd(TakeInVO takeInVO) {

    }
}
