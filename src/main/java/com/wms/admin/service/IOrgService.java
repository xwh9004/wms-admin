package com.wms.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.entity.OrgEntity;
import com.wms.admin.vo.OrgVO;

import java.util.List;

/**
 * <p>
 * 组织表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
public interface IOrgService extends IService<OrgEntity> {

    void addTopOrg(OrgVO orgVO);

    void addOrg(OrgVO orgVO);

    void updateOrg(OrgVO orgVO);

    void deleteOrg(String orgId);

    List<OrgVO> queryList();
}
