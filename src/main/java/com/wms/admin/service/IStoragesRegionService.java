package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.StoragesRegionEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.StoragesRegionQueryVO;
import com.wms.admin.vo.StoragesRegionVO;

import java.util.List;

/**
 * <p>
 * 仓库库区表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
public interface IStoragesRegionService extends IService<StoragesRegionEntity> {

    List<StoragesRegionVO> regionList();

    IPage<StoragesRegionVO> regionPages(StoragesRegionQueryVO storagesRegionQueryVO, PageParam pageParam);

    void addRegion(StoragesRegionVO storagesRegionVO);

    void updateRegion(StoragesRegionVO storagesRegionVO);

    void deleteRegion(String id);

}
