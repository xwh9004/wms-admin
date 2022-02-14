package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IStoragesRegionService;
import com.wms.admin.service.IVendorService;
import com.wms.admin.vo.StoragesRegionQueryVO;
import com.wms.admin.vo.StoragesRegionVO;
import com.wms.admin.vo.VendorQueryVO;
import com.wms.admin.vo.VendorVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 供应商表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
@RestController
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    private IVendorService vendorService;


    @ApiOperation(value = "供应商列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody VendorQueryVO queryVO, PageParam pageParam) {
        IPage<VendorVO> pages = vendorService.vendorPages(queryVO, pageParam);
        return Result.success().data(pages);
    }

    @ApiOperation(value = "新增供应商")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody VendorVO vendorVO) {
        vendorService.addVendor(vendorVO);
        return Result.success();
    }

    @ApiOperation(value = "修改供应商")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody VendorVO vendorVO) {
        vendorService.updateVendor(vendorVO);
        return Result.success();
    }


    @ApiOperation(value = "删除供应商")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/delete/{vendorId}")
    public Result delete(@PathVariable String vendorId) {
        vendorService.deleteVendor(vendorId);
        return Result.success();
    }
}
