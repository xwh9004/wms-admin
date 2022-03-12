package com.wms.admin.controller;


import com.wms.admin.commom.Result;
import com.wms.admin.service.IOrgService;
import com.wms.admin.vo.MenuVO;
import com.wms.admin.vo.OrgVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 组织表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
@Api("组织控制器")
@RestController
@RequestMapping("/org")
public class OrgController {
    @Autowired
    private IOrgService orgService;
    

    @ApiOperation(value = "组织列表")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @GetMapping("/list")
    public Result list() {
        List<OrgVO> list = orgService.queryList();
        return Result.success().data(list);
    }

    @ApiOperation("添加顶层组织")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/addTop")
    public Result addTopOrg(@RequestBody OrgVO orgVO) {
        orgService.addTopOrg(orgVO);
        return Result.success();
    }

    @ApiOperation("添加组织")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated OrgVO orgVO) {
        orgService.addOrg(orgVO);
        return Result.success();
    }

    @ApiOperation("修改组织")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody OrgVO orgVO) {
        orgService.updateOrg(orgVO);
        return Result.success();
    }

    @ApiOperation("删除组织")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/delete/{orgId}")
    public Result deleteMenu(@PathVariable String orgId) {
        orgService.deleteOrg(orgId);
        return Result.success();
    }


}
