package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.LesseeInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.LesseeInfoAndAddressesVO;
import com.wms.admin.vo.LesseeInfoQueryVO;
import com.wms.admin.vo.LesseeInfoVO;

import java.util.List;

/**
 * <p>
 * 承租单位表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
public interface ILesseeInfoService extends IService<LesseeInfoEntity> {

    List<LesseeInfoVO> allLessees();

    IPage<LesseeInfoVO> lesseeList(LesseeInfoQueryVO queryVO, PageParam pageParam);

    LesseeInfoVO detail(Integer id);

    void addLessee(LesseeInfoVO infoVO);

    void updateLessee(LesseeInfoVO infoVO);

    void deleteLessee(Integer id);

    IPage<LesseeInfoAndAddressesVO> lesseeInfoAndAddressesList(LesseeInfoQueryVO queryVO, PageParam pageParam);
}
