package com.wms.admin.service.impl;

import com.wms.admin.dto.AnnualStatisticDto;
import com.wms.admin.dto.MonthStatisticDto;
import com.wms.admin.dto.ProductStatisticDto;
import com.wms.admin.dto.WeekStatisticDto;
import com.wms.admin.mapper.StatisticMapper;
import com.wms.admin.service.IStatisticService;
import com.wms.admin.vo.AnnualStatisticVO;
import com.wms.admin.vo.MonthStatisticVO;
import com.wms.admin.vo.ProductStatisticVO;
import com.wms.admin.vo.WeekStatisticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImpl implements IStatisticService {
    @Autowired
    private StatisticMapper statisticMapper;
    @Override
    public AnnualStatisticVO annualStatistic() {

        List<AnnualStatisticDto>  statisticDtos= statisticMapper.annulStatistic();
        AnnualStatisticVO statisticVO = new AnnualStatisticVO();
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
