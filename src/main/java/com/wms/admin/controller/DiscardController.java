package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IDiscardRecordService;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.RegionRackVO;
import com.wms.admin.vo.DiscardDetailRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品报废详情记录表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
@Api("报废控制器")
@RestController
@RequestMapping("/discard")
public class DiscardController {
    @Autowired
    private IDiscardRecordService discardRecordService;

    @ApiOperation(value = "报废列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        IPage<ReceiptRecordVO<DiscardDetailRecordVO>> pages = discardRecordService.listPage(queryVO, pageParam);
        return Result.success().data(pages);
    }

    @ApiOperation(value = "报废详情")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/detail/{receiptNo}")
    public Result detail(@PathVariable String receiptNo) {
        ReceiptRecordVO detail = discardRecordService.detail(receiptNo);
        return Result.success().data(detail);
    }

    @ApiOperation(value = "新增报废")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody ReceiptRecordVO<DiscardDetailRecordVO> recordVO) {
        recordVO.setReceiptType("BF");
        discardRecordService.addDiscard(recordVO);
        return Result.success();
    }
}
