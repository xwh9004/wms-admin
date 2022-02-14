package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IProdCategoryService;
import com.wms.admin.vo.ProdCategoryQueryVO;
import com.wms.admin.vo.ProdCategoryVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 货物大类表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-14 14:43:16
 */
@RestController
@RequestMapping("/category")
public class ProdCategoryController {
    @Autowired
    private IProdCategoryService prodCategoryService;


    @ApiOperation(value = "大类列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody ProdCategoryQueryVO queryVO, PageParam pageParam) {
        IPage<ProdCategoryVO> pages = prodCategoryService.categoryPages(queryVO, pageParam);
        return Result.success().data(pages);
    }

    @ApiOperation(value = "新增大类")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody ProdCategoryVO vo) {
        prodCategoryService.addCategory(vo);
        return Result.success();
    }

    @ApiOperation(value = "修改大类")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody ProdCategoryVO categoryVO) {
        prodCategoryService.updateCategory(categoryVO);
        return Result.success();
    }


    @ApiOperation(value = "删除大类")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/delete/{regionId}")
    public Result delete(@PathVariable String regionId) {
        prodCategoryService.deleteCategory(regionId);
        return Result.success();
    }

}
