package com.wms.admin.mapper;

import com.wms.admin.entity.StorageOutDetailRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.StorageOutDetailRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 出库详情记录表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-02-18 12:17:42
 */
public interface StorageOutDetailRecordMapper extends BaseMapper<StorageOutDetailRecordEntity> {

    List<StorageOutDetailRecordVO> storageOutDetailList(@Param("receiptNo") String receiptNo);
}
