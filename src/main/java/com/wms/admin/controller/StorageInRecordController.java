package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IStorageInRecordService;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.RegionRackVO;
import com.wms.admin.vo.StorageInDetailRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品入库详情记录表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
@Api("入库控制器")
@RestController
@RequestMapping("/storage-in")
public class StorageInRecordController {
    @Autowired
    private IStorageInRecordService storageInRecordService;

    @ApiOperation(value = "库区货架列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/racks/{regionId}")
    public Result regionRacks(@PathVariable("regionId") String regionId ) {
      List<RegionRackVO> list = storageInRecordService.regionRacks(regionId );
        return Result.success().data(list);
    }

    @ApiOperation(value = "入库列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        IPage<ReceiptRecordVO<StorageInDetailRecordVO>> pages = storageInRecordService.listPage(queryVO, pageParam);
        return Result.success().data(pages);
    }

    @ApiOperation(value = "入库详情")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/detail/{receiptNo}")
    public Result detail(@PathVariable String receiptNo) {
        ReceiptRecordVO detail = storageInRecordService.detail(receiptNo);
        return Result.success().data(detail);
    }

    @ApiOperation(value = "新增入库")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody ReceiptRecordVO<StorageInDetailRecordVO> recordVO) {
        recordVO.setReceiptType("RK");
        storageInRecordService.addStorageIn(recordVO);
        return Result.success();
    }
}
