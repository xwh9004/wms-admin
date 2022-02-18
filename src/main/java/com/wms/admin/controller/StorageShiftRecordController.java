package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IStorageInRecordService;
import com.wms.admin.service.IStorageShiftRecordService;
import com.wms.admin.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 调拨详情记录表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-18 12:17:42
 */
@Api("调拨控制器")
@RestController
@RequestMapping("/storage-shift")
public class StorageShiftRecordController {
    @Autowired
    private IStorageShiftRecordService storageShiftRecordService;
    
    @ApiOperation(value = "调拨列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        IPage<ReceiptRecordVO<StorageShiftDetailRecordVO>> pages = storageShiftRecordService.listPage(queryVO, pageParam);
        return Result.success().data(pages);
    }

    @ApiOperation(value = "调拨详情")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/detail/{receiptNo}")
    public Result detail(@PathVariable String receiptNo) {
        ReceiptRecordVO detail = storageShiftRecordService.detail(receiptNo);
        return Result.success().data(detail);
    }

    @ApiOperation(value = "新增调拨")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody ReceiptRecordVO<StorageShiftDetailRecordVO> recordVO) {

        storageShiftRecordService.addStorageShift(recordVO);
        return Result.success();
    }
}
