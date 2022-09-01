package com.wms.admin.mapper;

import com.wms.admin.entity.LeaseContractEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.ContractVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 合同表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
public interface LeaseContractMapper extends BaseMapper<LeaseContractEntity> {

    ContractVO contractDetail(@Param("id") Integer id);
}
