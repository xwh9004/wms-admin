package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.ITakeInRecordService;
import com.wms.admin.vo.ContractQueryVO;
import com.wms.admin.vo.ContractVO;
import com.wms.admin.vo.TakeInQueryVO;
import com.wms.admin.vo.TakeInVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 收货记录表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
@RestController
@RequestMapping("/take-in")
public class TakeInRecordController {

    @Autowired
    private ITakeInRecordService takeInRecordService;

    @ApiOperation(value = "收货单列表查询")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody TakeInQueryVO queryVO, PageParam pageParam){
        IPage<TakeInVO> page = takeInRecordService.takeInList(queryVO, pageParam);
        return Result.success().data(page);
    }

    @ApiOperation(value = "新增收货单")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody TakeInVO takeInVO){
        takeInRecordService.takeInAdd(takeInVO);
        return Result.success();
    }

    @ApiOperation(value = "修改收货单")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody TakeInVO takeInVO){
        takeInRecordService.takeInUpdate(takeInVO);
        return Result.success();
    }

    @ApiOperation(value = "删除收货单")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Integer id){
        TakeInVO result = takeInRecordService.detail(id);
        return Result.success().data(result);
    }

    @ApiOperation(value = "修改收货单")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/taken-in/{id}")
    public Result update(@PathVariable("id") Integer id){
        takeInRecordService.takenIn(id);
        return Result.success();
    }

}
