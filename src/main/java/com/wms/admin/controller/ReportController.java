package com.wms.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.entity.ShiftDetailRecordEntity;
import com.wms.admin.service.IReportService;
import com.wms.admin.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jesse
 * @since 2022-02-19 21:10:29
 */
@Api("报表控制器")
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IReportService reportService;

    @ApiOperation(value = "入库报表列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/storage-in/list")
    public Result storageInList(@RequestBody ReportQueryVO queryVO, PageParam pageParam) {
        IPage<ReportRecordVO> pages = reportService.storageInList(queryVO, pageParam);
        return Result.success().data(pages);
    }
    @ApiOperation(value = "出库报表列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/storage-out/list")
    public Result storageOutList(@RequestBody ReportQueryVO queryVO, PageParam pageParam) {
        IPage<ReportRecordVO> pages = reportService.storageOutList(queryVO, pageParam);
        return Result.success().data(pages);
    }
    @ApiOperation(value = "调拨报表列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/storage-shift/list")
    public Result storageShiftList(@RequestBody ReportQueryVO queryVO, PageParam pageParam) {
        IPage<ReportRecordVO> pages = reportService.storageShiftList(queryVO, pageParam);
        return Result.success().data(pages);
    }
    @ApiOperation(value = "报废报表列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/discard/list")
    public Result discardList(@RequestBody ReportQueryVO queryVO, PageParam pageParam) {
        IPage<ReportRecordVO> pages = reportService.discardList(queryVO, pageParam);
        return Result.success().data(pages);
    }
    @ApiOperation(value = "报废报表列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/inventory/list")
    public Result inventoryList(@RequestBody ReportQueryVO queryVO, PageParam pageParam) {
        IPage<ReportRecordVO> pages = reportService.inventoryList(queryVO, pageParam);
        return Result.success().data(pages);
    }
    @ApiOperation(value = "货物列表报表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/product/list")
    public Result productList(@RequestBody ReportQueryVO queryVO, PageParam pageParam) {
        IPage<ProductVO> pages = reportService.productList(queryVO, pageParam);
        return Result.success().data(pages);
    }
    @ApiOperation(value = "库存列表报表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/stock/list")
    public Result stockList(@RequestBody ReportQueryVO queryVO, PageParam pageParam) {
        IPage<ProdStockVO> pages = reportService.stockList(queryVO, pageParam);
        return Result.success().data(pages);
    }
}
