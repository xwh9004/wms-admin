package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IStorageOutRecordService;
import com.wms.admin.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 出库详情记录表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-18 12:17:42
 */
@Api("出库控制器")
@RestController
@RequestMapping("/storage-out")
public class StorageOutRecordController {
    @Autowired
    private IStorageOutRecordService storageOutRecordService;
    
    @ApiOperation(value = "出库列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        IPage<ReceiptRecordVO<StorageOutDetailRecordVO>> pages = storageOutRecordService.listPage(queryVO, pageParam);
        return Result.success().data(pages);
    }

    @ApiOperation(value = "出库详情")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/detail/{receiptNo}")
    public Result detail(@PathVariable String receiptNo) {
        ReceiptRecordVO detail = storageOutRecordService.detail(receiptNo);
        return Result.success().data(detail);
    }

    @ApiOperation(value = "新增出库")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody ReceiptRecordVO<StorageOutDetailRecordVO> recordVO) {
        recordVO.setReceiptType("CK");
        storageOutRecordService.addStorageOut(recordVO);
        return Result.success();
    }
}
