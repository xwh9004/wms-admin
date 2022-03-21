package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ProdCategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.ProdCategoryQueryVO;
import com.wms.admin.vo.ProdCategoryVO;
import com.wms.admin.vo.StoragesRegionVO;

import java.util.List;

/**
 * <p>
 * 货物大类表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-14 14:43:16
 */
public interface IProdCategoryService extends IService<ProdCategoryEntity> {

    List<ProdCategoryVO> categoryAll();

    IPage<ProdCategoryVO> categoryPages(ProdCategoryQueryVO queryVO, PageParam pageParam);

    boolean addCategory(ProdCategoryVO vo);

    boolean updateCategory(ProdCategoryVO categoryVO);

    boolean deleteCategory(String regionId);

}
