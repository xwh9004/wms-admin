package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.service.ITakeInRecordService;
import com.wms.admin.service.ITakeOutRecordService;
import com.wms.admin.vo.TakeInQueryVO;
import com.wms.admin.vo.TakeInVO;
import com.wms.admin.vo.TakeOutQueryVO;
import com.wms.admin.vo.TakeOutVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 发货记录表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
@RestController
@RequestMapping("/take-out")
public class TakeOutRecordController {
    @Autowired
    private ITakeOutRecordService takeOutRecordService;

    @ApiOperation(value = "发货单列表查询")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody TakeOutQueryVO queryVO, PageParam pageParam){
        IPage<TakeOutVO> page = takeOutRecordService.takeOutList(queryVO, pageParam);
        return Result.success().data(page);
    }

    @ApiOperation(value = "新增发货单")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/save")
    public Result save(@RequestBody TakeOutVO takeOutVO){
        takeOutVO.setStatus(WMSConstants.TAKE_OUT_INIT);
        takeOutRecordService.takeOutAdd(takeOutVO);
        return Result.success();
    }

    @ApiOperation(value = "新增发货单")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/submit")
    public Result submit(@RequestBody TakeOutVO takeOutVO){
        takeOutVO.setStatus(WMSConstants.TAKEN_OUT);
        takeOutRecordService.takeOutAdd(takeOutVO);
        return Result.success();
    }


    @ApiOperation(value = "修改收货单")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody TakeOutVO takeOutVO){
        takeOutRecordService.takeOutUpdate(takeOutVO);
        return Result.success();
    }

    @ApiOperation(value = "出货单详情")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/detail/{id}")
    public Result detail(@PathVariable("id") String id){
        TakeOutVO result = takeOutRecordService.detail(Integer.valueOf(id));
        return Result.success().data(result);
    }

    @ApiOperation(value = "出货单确认出库")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/taken-out/{id}")
    public Result takenIn(@PathVariable("id") String id){
        takeOutRecordService.takenOut(Integer.valueOf(id));
        return Result.success();
    }
}
