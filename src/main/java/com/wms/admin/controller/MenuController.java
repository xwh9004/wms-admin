package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IMenuService;
import com.wms.admin.vo.MenuVO;
import com.wms.admin.vo.RouteVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-01-19 16:02:56
 */
@Api(tags = "菜单控制器")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

//    /**
//     * 没有使用
//     * @return
//     */
//    @Deprecated
//    @ApiOperation(value = "权限配置使用 所有路径")
//    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
//    @GetMapping("/routes")
//    public Result routes() {
//        List<RouteVO> menuVOList = menuService.queryRoutes();
//        return Result.success().data(menuVOList);
//    }

    @ApiOperation(value = "菜单列表")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @GetMapping("/list")
    public Result menuList() {
        List<MenuVO> menuVOList = menuService.queryList();
        return Result.success().data(menuVOList);
    }

    @ApiOperation("添加顶层菜单")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/addTop")
    public Result addTopMenu(@RequestBody @Validated MenuVO menuVO) {
        menuService.addTopMenu(menuVO);
        return Result.success();
    }

    @ApiOperation("添加菜单")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated MenuVO menuVO) {
        menuService.addMenu(menuVO);
        return Result.success();
    }

    @ApiOperation("修改菜单")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody @Validated MenuVO menuVO) {
        menuService.updateMenu(menuVO);
        return Result.success();
    }

    @ApiOperation("删除菜单")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "Token")
    @PostMapping("/delete/{menuId}")
    public Result deleteMenu(@PathVariable String menuId) {
        menuService.deleteMenu(menuId);
        return Result.success();
    }


}
