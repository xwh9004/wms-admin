package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IStockService;
import com.wms.admin.vo.StockInventoryQueryVO;
import com.wms.admin.vo.StockInventoryVO;
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
    private IStockService stockService;

    @ApiOperation(value = "库存盘点列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody StockInventoryQueryVO queryVO, PageParam pageParam) {
        IPage<StockInventoryVO> rolePage = stockService.inventoryList(queryVO,pageParam);
        return Result.success().data(rolePage);
    }

    @ApiOperation(value = "库存盘点详情")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/detail/{receiptNo}")
    public Result delete(@PathVariable String receiptNo) {
        stockService.inventoryDetail(receiptNo);
        return Result.success();
    }
    @ApiOperation(value = "库存盘点列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody StockInventoryVO queryVO) {
          stockService.inventoryAdd(queryVO);
        return Result.success();
    }
    @ApiOperation(value = "库存盘点列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("update")
    public Result update(@RequestBody StockInventoryVO queryVO) {
        stockService.inventoryUpdate(queryVO);
        return Result.success();
    }

}
