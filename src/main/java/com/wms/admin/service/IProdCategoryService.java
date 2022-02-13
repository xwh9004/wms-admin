package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ProdCategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.ProdCategoryQueryVO;
import com.wms.admin.vo.ProdCategoryVO;
import com.wms.admin.vo.StoragesRegionVO;

/**
 * <p>
 * 货物大类表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:21
 */
public interface IProdCategoryService extends IService<ProdCategoryEntity> {

    IPage<ProdCategoryVO> categoryPages(ProdCategoryQueryVO queryVO, PageParam pageParam);

    void addCategory(ProdCategoryVO vo);

    void updateCategory(ProdCategoryVO categoryVO);

    void deleteCategory(String regionId);
}
