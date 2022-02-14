package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IStoragesRegionService;
import com.wms.admin.vo.RoleQueryVO;
import com.wms.admin.vo.RoleVO;
import com.wms.admin.vo.StoragesRegionQueryVO;
import com.wms.admin.vo.StoragesRegionVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 仓库库区表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
@RestController
@RequestMapping("/region")
public class StoragesRegionController {

    @Autowired
    private IStoragesRegionService storagesRegionService;

    @ApiOperation(value = "库区列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/all")
    public Result all(@RequestBody StoragesRegionQueryVO queryVO, PageParam pageParam) {
        List<StoragesRegionVO> data = storagesRegionService.regionList();
        return Result.success().data(data);
    }

    @ApiOperation(value = "库区列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody StoragesRegionQueryVO queryVO, PageParam pageParam) {
        IPage<StoragesRegionVO> pages = storagesRegionService.regionPages(queryVO, pageParam);
        return Result.success().data(pages);
    }

    @ApiOperation(value = "新增库区")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody StoragesRegionVO regionVO) {
        storagesRegionService.addRegion(regionVO);
        return Result.success();
    }

    @ApiOperation(value = "修改库区")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody StoragesRegionVO regionVO) {
        storagesRegionService.updateRegion(regionVO);
        return Result.success();
    }


    @ApiOperation(value = "删除库区")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/delete/{regionId}")
    public Result delete(@PathVariable String regionId) {
        storagesRegionService.deleteRegion(regionId);
        return Result.success();
    }

}
