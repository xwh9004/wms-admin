package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.TakeOutRecordEntity;
import com.wms.admin.mapper.TakeOutRecordMapper;
import com.wms.admin.service.ITakeOutRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.vo.TakeInVO;
import com.wms.admin.vo.TakeOutQueryVO;
import com.wms.admin.vo.TakeOutVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 发货记录表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
@Service
public class TakeOutRecordServiceImpl extends ServiceImpl<TakeOutRecordMapper, TakeOutRecordEntity> implements ITakeOutRecordService {

    @Override
    public IPage<TakeInVO> takeOutList(TakeOutQueryVO queryVO, PageParam pageParam) {
        return null;
    }

    @Override
    public void takeOutAdd(TakeOutVO takeOutVO) {

    }
}
