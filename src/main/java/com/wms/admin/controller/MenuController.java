package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IMenuService;
import com.wms.admin.vo.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@Api("菜单控制器")
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;


    @ApiOperation("菜单列表")
    @GetMapping("/list")
    public Result menuList(@RequestParam PageParam pageParam) {
        List<MenuVO> menuVOList = menuService.queryList();
        return Result.success().data(menuVOList);
    }

    @ApiOperation("添加顶层菜单")
    @PostMapping("/add/topMenu")
    public Result addTopMenu(@RequestBody MenuVO menuVO) {
        menuService.addTopMenu(menuVO);
        return Result.success();
    }


}
