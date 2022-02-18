package com.wms.admin.mapper;

import com.wms.admin.entity.StorageInDetailRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.StorageInDetailRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品库存变更记录表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
public interface StorageInDetailRecordMapper extends BaseMapper<StorageInDetailRecordEntity> {

    List<StorageInDetailRecordVO> storageInDetailList(@Param("receiptNo") String receiptNo);

}
