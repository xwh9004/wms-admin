package com.wms.admin.controller;


import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.ILeaseContractService;
import com.wms.admin.vo.ContractVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Result all(@RequestBody ContractVO contractVO, PageParam pageParam) {
        contractService.contractAll(contractVO,pageParam);
        return Result.success();
    }

    @ApiOperation(value = "合同列表查询")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody ContractVO contractVO, PageParam pageParam) {
        contractService.contractList(contractVO,pageParam);
        return Result.success();
    }

    @ApiOperation(value = "新增合同")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated ContractVO contractVO) {
        contractService.addContract(contractVO);
        return Result.success();
    }


    @ApiOperation(value = "删除合同")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        contractService.deleteContract(id);
        return Result.success();
    }
}
