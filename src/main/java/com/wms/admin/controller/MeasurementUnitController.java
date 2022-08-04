package com.wms.admin.controller;


import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IMeasurementUnitService;
import com.wms.admin.vo.MeasurementUnitVO;
import com.wms.admin.vo.ProductVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 计量单位表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
@RestController
@RequestMapping("/measurement-unit")
public class MeasurementUnitController {

    private IMeasurementUnitService measurementUnitService;

    @ApiOperation(value = "新增计量单位")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody MeasurementUnitVO vo, PageParam pageParam) {
        measurementUnitService.selectList(vo,pageParam);
        return Result.success();
    }

    @ApiOperation(value = "新增计量单位")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@Validated @RequestBody MeasurementUnitVO vo) {
        measurementUnitService.addUnit(vo);
        return Result.success();
    }

    @ApiOperation(value = "新增计量单位")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody MeasurementUnitVO vo) {
        measurementUnitService.updateUnit(vo);
        return Result.success();
    }
}
