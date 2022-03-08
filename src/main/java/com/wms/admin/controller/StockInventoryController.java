package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IInventoryRecordService;
import com.wms.admin.service.IStockService;
import com.wms.admin.vo.InventoryDetailRecordVO;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 库存盘点记录表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
@Api("库存盘点控制器")
@RestController
@RequestMapping("/stock/inventory")
public class StockInventoryController {

    @Autowired
    private IInventoryRecordService inventoryRecordService;

    @ApiOperation(value = "库存盘点列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        IPage<ReceiptRecordVO<InventoryDetailRecordVO>> rolePage = inventoryRecordService.inventoryPages(queryVO, pageParam);
        return Result.success().data(rolePage);
    }

    @ApiOperation(value = "库存盘点详情")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/detail/{receiptNo}")
    public Result detail(@PathVariable String receiptNo) {
        ReceiptRecordVO<InventoryDetailRecordVO> detail = inventoryRecordService.detail(receiptNo);
        return Result.success().data(detail);
    }

    @ApiOperation(value = "新增库存盘点")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody ReceiptRecordVO<InventoryDetailRecordVO> recordVO) {
        recordVO.setReceiptType("PD");
        inventoryRecordService.addInventory(recordVO);
        return Result.success();
    }

}
