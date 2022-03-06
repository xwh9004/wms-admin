package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IStockService;
import com.wms.admin.vo.StockMaintainQueryVO;
import com.wms.admin.vo.StockMaintainVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 产品库存维护记录表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
@Api("库存维护控制器")
@RestController
@RequestMapping("/stock/maintain")
public class StockMaintainController {

    @Autowired
    private IStockService stockService;

    @ApiOperation(value = "库存维护列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody StockMaintainQueryVO queryVO, PageParam pageParam) {
        IPage<StockMaintainVO> rolePage = stockService.maintainList(queryVO,pageParam);
        return Result.success().data(rolePage);
    }
    @ApiOperation(value = "库存维护新增")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody StockMaintainVO maintainVO) {
          stockService.maintainAdd(maintainVO);
        return Result.success();
    }
    @ApiOperation(value = "库存维护更新")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("update")
    public Result update(@RequestBody StockMaintainVO queryVO) {
        stockService.maintainUpdate(queryVO);
        return Result.success();
    }
    @ApiOperation(value = "库存维护列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        stockService.maintainDelete(id);
        return Result.success();
    }
}
