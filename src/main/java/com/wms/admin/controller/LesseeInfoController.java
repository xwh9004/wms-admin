package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.ILesseeInfoService;
import com.wms.admin.vo.LesseeInfoAndAddressesVO;
import com.wms.admin.vo.LesseeInfoQueryVO;
import com.wms.admin.vo.LesseeInfoVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 承租单位表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
@RestController
@RequestMapping("/lessee")
public class LesseeInfoController {

    @Autowired
    private ILesseeInfoService lesseeInfoService;


    @ApiOperation("修改承租单位")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/all")
    public Result all() {
        List<LesseeInfoVO> result = lesseeInfoService.allLessees();
        return Result.success().data(result);
    }
    @ApiOperation("承租单位列表")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/list")
    public Result list(@Validated @RequestBody LesseeInfoQueryVO queryVO, PageParam pageParam) {
        IPage<LesseeInfoVO> result = lesseeInfoService.lesseeList(queryVO, pageParam);
        return Result.success().data(result);
    }

    @ApiOperation("承租单位和地址列表")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/lessees")
    public Result lesseeAndAddressesList(@Validated @RequestBody LesseeInfoQueryVO queryVO, PageParam pageParam) {
        IPage<LesseeInfoAndAddressesVO> result = lesseeInfoService.lesseeInfoAndAddressesList(queryVO, pageParam);
        return Result.success().data(result);
    }

    @ApiOperation("删除承租单位")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Integer id) {
        LesseeInfoVO lesseeInfoVO =lesseeInfoService.detail(id);
        return Result.success().data(lesseeInfoVO);
    }

    @ApiOperation("添加承租单位")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/add")
    public Result add(@Validated @RequestBody LesseeInfoVO infoVO) {
        lesseeInfoService.addLessee(infoVO);
        return Result.success();
    }

    @ApiOperation("修改承租单位")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody LesseeInfoVO infoVO) {
        lesseeInfoService.updateLessee(infoVO);
        return Result.success();
    }

    @ApiOperation("删除承租单位")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        lesseeInfoService.deleteLessee(id);
        return Result.success();
    }
}
