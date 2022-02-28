package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IProdCategoryService;
import com.wms.admin.service.IProductService;
import com.wms.admin.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 货物表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
@Api("货物控制器")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;


    @ApiOperation(value = "货物列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody ProductQueryVO queryVO, PageParam pageParam) {
        IPage<ProductVO> pages = productService.productPages(queryVO, pageParam);
        return Result.success().data(pages);
    }
    @ApiOperation(value = "货物查询")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/findBy/{prodNo}")
    public Result findBy(@PathVariable String prodNo) {
        ProductVO product = productService.findByProdNo(prodNo);
        return Result.success().data(product);
    }
    @ApiOperation(value = "新增货物")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody ProductVO vo) {
        productService.addProduct(vo);
        return Result.success();
    }

    @ApiOperation(value = "修改货物")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody ProductVO productVO) {
        productService.updateProduct(productVO);
        return Result.success();
    }


    @ApiOperation(value = "删除货物")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        productService.deleteProduct(id);
        return Result.success();
    }
}
