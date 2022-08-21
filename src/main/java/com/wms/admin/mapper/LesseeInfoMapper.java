package com.wms.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.entity.LesseeInfoEntity;
import com.wms.admin.vo.LesseeInfoAndAddressesVO;
import com.wms.admin.vo.LesseeInfoQueryVO;
import com.wms.admin.vo.LesseeInfoVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 承租单位表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
public interface LesseeInfoMapper extends BaseMapper<LesseeInfoEntity> {

   LesseeInfoVO lesseeDetail(@Param("id") Integer id);

   IPage<LesseeInfoAndAddressesVO> lesseeAndAddressesPage(@Param("param") LesseeInfoQueryVO queryVO, Page page);

}
