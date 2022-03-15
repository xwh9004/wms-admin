package com.wms.admin.controller;

import com.wms.admin.commom.Result;
import com.wms.admin.dto.AnnualStatisticDto;
import com.wms.admin.dto.MonthStatisticDto;
import com.wms.admin.dto.ProductStatisticDto;
import com.wms.admin.dto.WeekStatisticDto;
import com.wms.admin.service.IStatisticService;
import com.wms.admin.vo.AnnualStatisticVO;
import com.wms.admin.vo.MonthStatisticVO;
import com.wms.admin.vo.ProductStatisticVO;
import com.wms.admin.vo.WeekStatisticVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 全景视图 控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
@Api("全景视图控制器")
@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private IStatisticService statisticService;

    @ApiOperation(value = "年度统计")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/annual")
    public Result annualStatistic() {
        AnnualStatisticVO data = statisticService.annualStatistic();
        return Result.success().data(data);
    }

    @ApiOperation(value = "月度统计")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/month")
    public Result monthStatistic() {
        List<MonthStatisticVO> data = statisticService.monthStatistic();
        return Result.success().data(data);
    }

    @ApiOperation(value = "周统计")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/weekly")
    public Result weeklyStatistic() {
        List<WeekStatisticVO> data = statisticService.weeklyStatistic();
        return Result.success().data(data);
    }

    @ApiOperation(value = "货物统计")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/product")
    public Result productStatistic() {
        ProductStatisticVO data = statisticService.productStatistic();
        return Result.success().data(data);
    }
}
