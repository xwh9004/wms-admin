package com.wms.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.entity.ShiftDetailRecordEntity;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.StorageShiftDetailRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 调拨详情记录表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-02-18 12:17:42
 */
public interface StorageShiftDetailRecordMapper extends BaseMapper<ShiftDetailRecordEntity> {
    ReceiptRecordVO selectByReceiptNo(@Param(value = "receiptNo") String receiptNo);

    IPage<ReceiptRecordVO<StorageShiftDetailRecordVO>> receiptPage(@Param(value = "param") ReceiptRecordQueryVO queryVO, Page page);

    List<StorageShiftDetailRecordVO> storageShiftDetailList(@Param("receiptNo") String receiptNo);
}
