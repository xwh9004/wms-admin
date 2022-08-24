package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.ILeaseContractService;
import com.wms.admin.vo.ContractQueryVO;
import com.wms.admin.vo.ContractVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 合同表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
@Api(tags = "合同管理")

@RestController
@RequestMapping("/contract")
public class LeaseContractController {

    @Autowired
    private ILeaseContractService contractService;

    @ApiOperation(value = "所有合同编号")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/all")
    public Result all() {
        List<ContractVO> list = contractService.contractAll();
        return Result.success().data(list);
    }

    @ApiOperation(value = "合同列表查询")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody ContractQueryVO queryVO, PageParam pageParam) {
        IPage<ContractVO> page = contractService.contractList(queryVO, pageParam);
        return Result.success().data(page);
    }

    @ApiOperation(value = "新增合同")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated ContractVO contractVO) {
        contractService.addContract(contractVO);
        return Result.success();
    }

    @ApiOperation(value = "新增合同")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody @Validated ContractVO contractVO) {
        contractService.updateContract(contractVO);
        return Result.success();
    }


    @ApiOperation(value = "合同失效")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/ineffective/{id}")
    public Result ineffective(@PathVariable("id") Integer id) {
        contractService.ineffectiveContract(id);
        return Result.success();
    }
    @ApiOperation(value = "合同失效")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/effect/{id}")
    public Result effect(@PathVariable("id") Integer id) {
        contractService.effectContract(id);
        return Result.success();
    }

    @ApiOperation(value = "合同删除")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        contractService.deleteContract(id);
        return Result.success();
    }
}
