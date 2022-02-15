package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IRegionRacksService;
import com.wms.admin.vo.RegionRackQueryVO;
import com.wms.admin.vo.RegionRackVO;
import com.wms.admin.vo.StoragesRegionVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 库区货架表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
@RestController
@RequestMapping("/racks")
public class RegionRacksController {
    @Autowired
    private IRegionRacksService regionRacksService;


    @ApiOperation(value = "货架列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody RegionRackQueryVO queryVO, PageParam pageParam) {
        IPage<RegionRackVO> pages = regionRacksService.regionRackPages(queryVO, pageParam);
        return Result.success().data(pages);
    }

    @ApiOperation(value = "新增货架")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody RegionRackVO vo) {
        regionRacksService.addRegionRack(vo);
        return Result.success();
    }

    @ApiOperation(value = "修改货架")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody RegionRackVO regionRackVO) {
        regionRacksService.updateRegionRack(regionRackVO);
        return Result.success();
    }


    @ApiOperation(value = "删除货架")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/delete/{regionId}")
    public Result delete(@PathVariable String rackId) {
        regionRacksService.deleteRegionRack(rackId);
        return Result.success();
    }
}
