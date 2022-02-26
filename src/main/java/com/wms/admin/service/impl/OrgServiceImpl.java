package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.OrgEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.OrgMapper;
import com.wms.admin.service.IOrgService;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.OrgVO;
import javafx.beans.binding.Bindings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 组织表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
@Slf4j
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, OrgEntity> implements IOrgService {

    private static final String SLASH = "/";
    private static final int TOP_LEVEL = 1;

    @Override
    public List<OrgVO> queryList() {
        LambdaQueryWrapper<OrgEntity> listCond = new LambdaQueryWrapper<>();
        listCond.eq(OrgEntity::getDelFlag, WMSConstants.DEL_FLG_1);
        listCond.orderByAsc(OrgEntity::getLevelNo);
        listCond.orderByAsc(OrgEntity::getSeq);
        final List<OrgEntity> orgEntities = this.baseMapper.selectList(listCond);
        List<OrgVO> list = toOrgTree(orgEntities);
        return list;
    }

    private List<OrgVO> toOrgTree(List<OrgEntity> orgEntities) {

        Map<String, OrgVO> entityMap = orgEntities.stream()
                .collect(Collectors.toMap(OrgEntity::getId, OrgEntity::toOrgVO));
        List<OrgVO> list = new ArrayList<>();
        orgEntities.stream().forEach(orgEntity -> {
            if (TOP_LEVEL == orgEntity.getLevelNo()) {
                list.add(entityMap.get(orgEntity.getId()));
            } else {
                OrgVO parentOrgVO = entityMap.get(orgEntity.getParentId());
                OrgVO orgVO = entityMap.get(orgEntity.getId());
                parentOrgVO.addChild(orgVO);
            }
        });
        return list;
    }


    @Override
    public void addTopOrg(OrgVO orgVO) {
        log.info("创建顶层组织");
        checkForAddTopOrg(orgVO);
        OrgEntity orgEntity = new OrgEntity();
        orgEntity.setId(UUIDUtil.uuid());
        orgEntity.setLevelNo(1);
        orgEntity.setSeq(orgVO.getSeq());
        orgEntity.setOrgCode(orgVO.getOrgCode());
        orgEntity.setOrgName(orgVO.getOrgName());
        orgEntity.setParentId("-1"); //top org parentId is -1;
        orgEntity.setCreateBy(UserInfoContext.getUsername());
        orgEntity.setUpdateBy(UserInfoContext.getUsername());
        save(orgEntity);

    }

    private void checkForAddTopOrg(OrgVO orgVO) {
        checkOrgSeq(null, orgVO.getSeq(), 1,"-1");
        //check orgCode existed;
        checkOrgCode(null, orgVO.getOrgCode());
    }

    private void checkOrgSeq(String excludeId, Integer seq, int level,String parentId) {
        LambdaQueryWrapper<OrgEntity> codeQueryCond = new LambdaQueryWrapper<>();
        codeQueryCond
                .eq(OrgEntity::getSeq, seq)
                .eq(OrgEntity::getDelFlag, WMSConstants.DEL_FLG_1)
                .eq(OrgEntity::getLevelNo, level)
                .eq(OrgEntity::getParentId, parentId);
        if (StringUtils.isNotBlank(excludeId)) {
            codeQueryCond.ne(OrgEntity::getId, excludeId);
        }
        final OrgEntity orgEntity = this.baseMapper.selectOne(codeQueryCond);
        if (Objects.nonNull(orgEntity)) {
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "同级组织，序号" + seq);
        }
    }

    private void checkOrgCode(String excludeId, String orgCode) {
        LambdaQueryWrapper<OrgEntity> codeQueryCond = new LambdaQueryWrapper<>();
        codeQueryCond
                .eq(OrgEntity::getOrgCode, orgCode)
                .eq(OrgEntity::getDelFlag, WMSConstants.DEL_FLG_1);
        if (StringUtils.isNotBlank(excludeId)) {
            codeQueryCond.ne(OrgEntity::getId, excludeId);
        }
        final OrgEntity orgEntity = this.baseMapper.selectOne(codeQueryCond);
        if (Objects.nonNull(orgEntity)) {
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "组织编号" + orgCode);
        }
    }

    @Override
    public void addOrg(OrgVO orgVO) {
        log.info("创建组织");
        checkForAddOrg(orgVO);
        OrgEntity orgEntity = new OrgEntity();
        orgEntity.setId(UUIDUtil.uuid());
        orgEntity.setOrgCode(orgVO.getOrgCode());
        orgEntity.setOrgName(orgVO.getOrgName());
        orgEntity.setSeq(orgVO.getSeq());
        orgEntity.setLevelNo(orgVO.getLevelNo());
        orgEntity.setParentId(orgVO.getParentId());
        orgEntity.setLevelPath(orgEntity.getParentId() + SLASH + orgEntity.getId());
        orgEntity.setCreateBy(UserInfoContext.getUsername());
        orgEntity.setUpdateBy(UserInfoContext.getUsername());
        save(orgEntity);
    }

    private void checkForAddOrg(OrgVO orgVO) {
        if (StringUtils.isBlank(orgVO.getParentId())) {
            throw new BusinessException(ResultCode.PARAM_NOT_NULL, "父组织ID");
        }
        OrgEntity parentOrg = this.baseMapper.selectById(orgVO.getParentId());
        if (parentOrg == null || WMSConstants.DEL_FLG_0.equals(parentOrg.getDelFlag())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "父组织");
        }
        checkOrgSeq(null, orgVO.getSeq(), parentOrg.getLevelNo() + 1, orgVO.getParentId());
        checkOrgCode(null, orgVO.getOrgCode());
        orgVO.setLevelNo(parentOrg.getLevelNo() + 1);
    }

    @Override
    public void updateOrg(OrgVO orgVO) {
        checkOrgForUpdate(orgVO);
        OrgEntity orgEntity = new OrgEntity();
        orgEntity.setId(orgVO.getId());
        orgEntity.setOrgCode(orgVO.getOrgCode());
        orgEntity.setOrgName(orgVO.getOrgName());
        orgEntity.setSeq(orgVO.getSeq());
        orgEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(orgEntity);
    }

    private void checkOrgForUpdate(OrgVO orgVO) {
        checkOrgId(orgVO);
        checkOrgSeq(orgVO.getId(), orgVO.getSeq(), orgVO.getLevelNo(), orgVO.getParentId());
        checkOrgCode(orgVO.getId(), orgVO.getOrgCode());
    }

    private void checkOrgId(OrgVO orgVO) {

        final OrgEntity orgEntity = this.baseMapper.selectById(orgVO.getId());
        if (Objects.nonNull(orgEntity) &&
                WMSConstants.DEL_FLG_0.equals(orgEntity.getDelFlag())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "组织不存在");
        }
    }

    @Override
    public void deleteOrg(String orgId) {
        OrgEntity orgEntity = new OrgEntity();
        orgEntity.setId(orgId);
        orgEntity.setDelFlag(WMSConstants.DEL_FLG_0);
        orgEntity.setUpdateBy(UserInfoContext.getUsername());
        this.baseMapper.updateById(orgEntity);
        log.info("删除组织 [id={}]成功", orgId);

    }

}
