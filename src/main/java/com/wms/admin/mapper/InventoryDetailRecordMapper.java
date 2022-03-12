package com.wms.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.entity.InventoryDetailRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.InventoryDetailRecordVO;
import com.wms.admin.vo.ReportQueryVO;
import com.wms.admin.vo.ReportRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 盘点详情录表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-02-27 20:57:49
 */
public interface InventoryDetailRecordMapper extends BaseMapper<InventoryDetailRecordEntity> {

    List<InventoryDetailRecordVO> inventoryDetailListBy(@Param("receiptNo") String receiptNo);

    IPage<ReportRecordVO> inventoryReportList(@Param(value = "query") ReportQueryVO queryVO, Page page);

}
