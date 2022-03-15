package com.wms.admin.service;

import com.wms.admin.vo.AnnualStatisticVO;
import com.wms.admin.vo.MonthStatisticVO;
import com.wms.admin.vo.ProductStatisticVO;
import com.wms.admin.vo.WeekStatisticVO;

import java.util.List;

public interface IStatisticService {

    AnnualStatisticVO annualStatistic();

    List<MonthStatisticVO> monthStatistic();

    List<WeekStatisticVO> weeklyStatistic();

    ProductStatisticVO productStatistic();


}
