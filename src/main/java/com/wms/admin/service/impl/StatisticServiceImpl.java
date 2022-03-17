package com.wms.admin.service.impl;

import com.wms.admin.dto.AnnualStatisticDto;
import com.wms.admin.dto.MonthStatisticDto;
import com.wms.admin.dto.WeekStatisticDto;
import com.wms.admin.enumeration.ReceiptType;
import com.wms.admin.mapper.StatisticMapper;
import com.wms.admin.service.IStatisticService;
import com.wms.admin.vo.AnnualStatisticVO;
import com.wms.admin.vo.MonthStatisticVO;
import com.wms.admin.vo.ProductStatisticVO;
import com.wms.admin.vo.WeekStatisticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticServiceImpl implements IStatisticService {
    @Autowired
    private StatisticMapper statisticMapper;
    @Override
    public AnnualStatisticVO annualStatistic() {
        List<AnnualStatisticDto>  statisticDtos= statisticMapper.annulStatistic();
        Optional.ofNullable(statisticDtos).orElse(new ArrayList<>());
        AnnualStatisticVO statisticVO = new AnnualStatisticVO();
        statisticDtos.forEach(item->statisticVO.setAmount(item.getReceiptType(),item.getTotalAmount()));
        return statisticVO;
    }

    @Override
    public List<MonthStatisticVO> monthStatistic() {
        List<MonthStatisticDto> statisticDtos = statisticMapper.monthStatistic();
        MonthStatisticVO monthStatisticVO = new MonthStatisticVO();
        return null;
    }

    @Override
    public List<WeekStatisticVO> weeklyStatistic() {
        List<WeekStatisticDto> statisticDtos = statisticMapper.weekStatistic();
        WeekStatisticVO weekStatisticVO = new WeekStatisticVO();
        return null;
    }

    @Override
    public ProductStatisticVO productStatistic() {
        return null;
    }
}
