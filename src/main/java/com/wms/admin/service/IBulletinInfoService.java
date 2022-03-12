package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.dto.BulletinInfoDto;
import com.wms.admin.dto.BulletinQueryDto;
import com.wms.admin.entity.BulletinInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.BulletinInfoVO;
import com.wms.admin.vo.BulletinQueryVO;
import com.wms.admin.vo.ProdCategoryQueryVO;
import com.wms.admin.vo.ProdCategoryVO;

/**
 * <p>
 * 布告信息表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:48
 */
public interface IBulletinInfoService extends IService<BulletinInfoEntity> {

    IPage<BulletinInfoDto> bulletinPages(BulletinQueryDto queryCond);

    void addBulletin(BulletinInfoVO bulletinVO);

    void updateBulletin(BulletinInfoVO bulletinVO);

    void deleteBulletin(Integer menuId);

    void publish(Integer id);

    BulletinInfoDto findPublished();
}
