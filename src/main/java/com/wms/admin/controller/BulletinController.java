package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.dto.BulletinInfoDto;
import com.wms.admin.dto.BulletinQueryDto;
import com.wms.admin.service.IBulletinInfoService;
import com.wms.admin.vo.BulletinInfoVO;
import com.wms.admin.vo.BulletinQueryVO;
import com.wms.admin.vo.ProdCategoryVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 布告信息表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:48
 */
@RestController
@RequestMapping("/bulletin")
public class BulletinController {
    @Autowired
    private IBulletinInfoService bulletinService;

    @ApiOperation(value = "公告列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody BulletinQueryVO queryVO, @Validated PageParam pageParam) {
        BulletinQueryDto queryCond = new BulletinQueryDto();
        BeanUtils.copyProperties(queryVO, queryCond);
        BeanUtils.copyProperties(pageParam, queryCond);
        IPage<BulletinInfoDto> pages = bulletinService.bulletinPages(queryCond);
        IPage<BulletinInfoVO> result = pages.convert(item -> {
            BulletinInfoVO bulletinInfo = new BulletinInfoVO();
            bulletinInfo.setId(item.getId());
            bulletinInfo.setBulletinInfo(item.getBulletinInfo());
            bulletinInfo.setIsPublish(item.getIsPublish());
            bulletinInfo.setCreateBy(item.getCreateBy());
            bulletinInfo.setCreateTime(item.getCreateTime());
            return bulletinInfo;
        });
        return Result.success().data(result);
    }

    @ApiOperation("查找发布公告")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/published")
    public Result published() {
        BulletinInfoDto bulletinInfo = bulletinService.findPublished();
        return Result.success().data(bulletinInfo.getBulletinInfo());
    }

    @ApiOperation("添加公告")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated BulletinInfoVO bulletinVO) {
        bulletinService.addBulletin(bulletinVO);
        return Result.success();
    }

    @ApiOperation("修改公告")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody BulletinInfoVO bulletinVO) {
        bulletinService.updateBulletin(bulletinVO);
        return Result.success();
    }

    @ApiOperation("删除公告")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/delete/{id}")
    public Result deleteMenu(@PathVariable Integer id) {
        bulletinService.deleteBulletin(id);
        return Result.success();
    }

    @ApiOperation("发布公告")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/publish/{id}")
    public Result publish(@PathVariable Integer id) {
        bulletinService.publish(id);
        return Result.success();
    }

}
