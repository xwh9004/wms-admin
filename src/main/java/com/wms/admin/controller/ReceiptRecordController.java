package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IReceiptRecordService;
import com.wms.admin.vo.ProductQueryVO;
import com.wms.admin.vo.ProductVO;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 申请记录表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
@Api("单据控制器")
@RestController
@RequestMapping("/receipt")
public class ReceiptRecordController {

    @Autowired
    private IReceiptRecordService receiptRecordService;

    @ApiOperation(value = "单据列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        IPage<ReceiptRecordVO<Object>> pages = receiptRecordService.receiptPage(queryVO, pageParam);
        return Result.success().data(pages);
    }

}
