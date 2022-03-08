package com.wms.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.entity.DiscardDetailRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.DiscardDetailRecordVO;
import com.wms.admin.vo.ReportQueryVO;
import com.wms.admin.vo.ReportRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 报废详情录表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
public interface DiscardDetailRecordMapper extends BaseMapper<DiscardDetailRecordEntity> {

    List<DiscardDetailRecordVO> discardDetailListBy(@Param("receiptNo") String receiptNo);

    IPage<ReportRecordVO> discardReportList(@Param(value = "query") ReportQueryVO queryVO, Page page);

}
