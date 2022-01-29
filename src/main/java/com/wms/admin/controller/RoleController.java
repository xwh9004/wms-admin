package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IRoleService;
import com.wms.admin.vo.RoleQueryVO;
import com.wms.admin.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
@Api("角色控制器")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @ApiOperation(value = "角色列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/all")
    public Result all() {
        List<RoleVO> roleList = roleService.roleList();
        return Result.success().data(roleList);
    }
    @ApiOperation(value = "角色列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody RoleQueryVO queryVO,PageParam pageParam) {
        IPage<RoleVO> rolePage = roleService.rolePage(queryVO,pageParam);
        return Result.success().data(rolePage);
    }

    @ApiOperation(value = "新增角色")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody RoleVO roleVO) {
        roleService.addRole(roleVO);
        return Result.success();
    }

    @ApiOperation(value = "修改角色")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody RoleVO roleVO) {
        roleService.updateRole(roleVO);
        return Result.success();
    }


    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/delete/{roleId}")
    public Result delete(@PathVariable String roleId) {
        roleService.deleteRole(roleId);
        return Result.success();
    }

}
