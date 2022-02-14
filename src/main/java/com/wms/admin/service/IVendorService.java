package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.VendorEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.StoragesRegionVO;
import com.wms.admin.vo.VendorQueryVO;
import com.wms.admin.vo.VendorVO;

/**
 * <p>
 * 供应商表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
public interface IVendorService extends IService<VendorEntity> {

    IPage<VendorVO> vendorPages(VendorQueryVO queryVO, PageParam pageParam);

    void addVendor(VendorVO vendorVO);

    void updateVendor(VendorVO vendorVO);

    void deleteVendor(String vendorId);


}
