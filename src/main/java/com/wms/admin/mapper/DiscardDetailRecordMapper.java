package com.wms.admin.mapper;

import com.wms.admin.entity.DiscardDetailRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.DiscardDetailRecordVO;
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
}
