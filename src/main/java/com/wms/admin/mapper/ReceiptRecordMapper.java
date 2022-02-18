package com.wms.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.entity.ReceiptRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.ProductQueryVO;
import com.wms.admin.vo.ProductVO;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 申请记录表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
public interface ReceiptRecordMapper extends BaseMapper<ReceiptRecordEntity> {
    IPage<ReceiptRecordVO<?>> receiptPage(@Param(value = "param") ReceiptRecordQueryVO queryVO, Page page);

    ReceiptRecordVO selectByReceiptNo(@Param(value = "receiptNo") String receiptNo);
}
