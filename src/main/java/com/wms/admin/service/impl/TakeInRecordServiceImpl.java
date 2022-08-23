package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.TakeInDetailEntity;
import com.wms.admin.entity.TakeInRecordEntity;
import com.wms.admin.mapper.TakeInRecordMapper;
import com.wms.admin.service.ITakeInDetailService;
import com.wms.admin.service.ITakeInRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.SequenceUtil;
import com.wms.admin.vo.TakeInQueryVO;
import com.wms.admin.vo.TakeInVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ITakeInDetailService takeInDetailService;

    @Override
    public IPage<TakeInVO> takeInList(TakeInQueryVO queryVO, PageParam pageParam) {
        return null;
    }

    @Transactional
    @Override
    public void takeInAdd(TakeInVO takeInVO) {
        //goods receive
        final String takeInNo = SequenceUtil.generateNoByDate("GR");
        takeInVO.setTakeInNo(takeInNo);
        TakeInRecordEntity takeInRecordEntity = new TakeInRecordEntity();
        BeanUtils.copyProperties(takeInVO, takeInRecordEntity);
        save(takeInRecordEntity);
        saveDetails(takeInVO);
    }

    private void saveDetails(TakeInVO takeInVO) {
        if (!CollectionUtils.isEmpty(takeInVO.getProdList())) {
            List<TakeInDetailEntity> prodEntityList = takeInVO.getProdList()
                    .stream().map(vo -> {
                        TakeInDetailEntity detailEntity = new TakeInDetailEntity();
                        BeanUtils.copyProperties(vo, detailEntity);
                        detailEntity.setTakeInNo(takeInVO.getTakeInNo());
                        return detailEntity;
                    }).collect(Collectors.toList());
            takeInDetailService.saveBatch(prodEntityList);
        }
    }

    @Override
    public TakeInVO detail(Integer id) {
        return null;
    }

    @Override
    public void takeInUpdate(TakeInVO takeInVO) {

    }
}
