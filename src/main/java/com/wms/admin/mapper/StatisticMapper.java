package com.wms.admin.mapper;

import com.wms.admin.dto.AnnualStatisticDto;
import com.wms.admin.dto.MonthStatisticDto;
import com.wms.admin.dto.WeekStatisticDto;

import java.util.List;

public interface StatisticMapper {

    List<AnnualStatisticDto> annulStatistic();
    List<MonthStatisticDto> monthStatistic();
    List<WeekStatisticDto> weekStatistic();
}
