package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.dto.BulletinInfoDto;
import com.wms.admin.dto.BulletinQueryDto;
import com.wms.admin.entity.BulletinInfoEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.BulletinInfoMapper;
import com.wms.admin.service.IBulletinInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.vo.BulletinInfoVO;
import com.wms.admin.vo.BulletinQueryVO;
import com.wms.admin.vo.ProdCategoryVO;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

/**
 * <p>
 * 布告信息表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:48
 */
@Service
public class BulletinInfoServiceImpl extends ServiceImpl<BulletinInfoMapper, BulletinInfoEntity> implements IBulletinInfoService {

    private static final String PUBLISH_Y ="1";
    private static final String PUBLISH_N ="0";

    @Override
    public IPage<BulletinInfoDto> bulletinPages(BulletinQueryDto queryCond) {
        IPage<BulletinInfoEntity> page = new Page<>(queryCond.getPage(),queryCond.getLimit());
        LambdaQueryWrapper<BulletinInfoEntity> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(BulletinInfoEntity::getDelFlag,WMSConstants.DEL_FLG_1);
        if(queryCond.getStartTime()!=null){
            queryWrapper.gt(BulletinInfoEntity::getCreateTime,queryCond.getStartTime());
        }
        if(queryCond.getEndTime()!=null){
            queryWrapper.lt(BulletinInfoEntity::getCreateTime,queryCond.getEndTime());
        }
        IPage<BulletinInfoDto> result= page(page,queryWrapper).convert(item -> {
            BulletinInfoDto bulletinInfo = new BulletinInfoDto();
            BeanUtils.copyProperties(item,bulletinInfo);
            return bulletinInfo;
        });
        return result;
    }

    @Override
    public void addBulletin(BulletinInfoVO bulletinVO) {
        BulletinInfoEntity bulletinInfoEntity = new BulletinInfoEntity();
        BeanUtils.copyProperties(bulletinVO,bulletinInfoEntity);
        bulletinInfoEntity.setIsPublish("0");
        bulletinInfoEntity.setCreateBy(UserInfoContext.getUsername());
        bulletinInfoEntity.setUpdateBy(UserInfoContext.getUsername());
        save(bulletinInfoEntity);
    }

    @Transactional
    @Override
    public void updateBulletin(BulletinInfoVO bulletinVO) {
        BulletinInfoEntity bulletinInfoEntity = getBulletinInfoEntity(bulletinVO.getId());
        bulletinInfoEntity.setBulletinInfo(bulletinVO.getBulletinInfo());
        bulletinInfoEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(bulletinInfoEntity);
    }

    @Transactional
    private BulletinInfoEntity getBulletinInfoEntity(Integer id) {
        if (Objects.isNull(id)) {
            throw new BusinessException(ResultCode.PARAM_NOT_NULL, "公告id");
        }
        BulletinInfoEntity bulletinInfoEntity = getById(id);
        if (Objects.isNull(bulletinInfoEntity)) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "公告不存在");
        }
        return bulletinInfoEntity;
    }

    @Transactional
    @Override
    public void deleteBulletin(Integer id) {
        BulletinInfoEntity bulletinInfoEntity = getBulletinInfoEntity(id);
        bulletinInfoEntity.setDelFlag(WMSConstants.DEL_FLG_0);
        bulletinInfoEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(bulletinInfoEntity);
    }

    @Transactional
    @Override
    public void publish(Integer id) {
        BulletinInfoEntity bulletinInfoEntity = getBulletinInfoEntity(id);
        resetAll();
        bulletinInfoEntity.setIsPublish(PUBLISH_Y);
        bulletinInfoEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(bulletinInfoEntity);
    }

    @Override
    public BulletinInfoDto findPublished() {
        LambdaQueryWrapper<BulletinInfoEntity> publishedCond =new LambdaQueryWrapper<>();
        publishedCond
                .eq(BulletinInfoEntity::getDelFlag,WMSConstants.DEL_FLG_1)
                .eq(BulletinInfoEntity::getIsPublish,PUBLISH_Y);
        BulletinInfoEntity entity =getOne(publishedCond);
        BulletinInfoDto bulletinInfo = new BulletinInfoDto();
        BeanUtils.copyProperties(entity,bulletinInfo);
        return bulletinInfo;
    }

    public void resetAll(){
        LambdaUpdateWrapper<BulletinInfoEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(BulletinInfoEntity::getDelFlag,WMSConstants.DEL_FLG_1)
        .eq(BulletinInfoEntity::getIsPublish,PUBLISH_Y)
                .set(BulletinInfoEntity::getIsPublish,PUBLISH_N)
                .set(BulletinInfoEntity::getUpdateBy,UserInfoContext.getUsername());
        update(updateWrapper);
    }

}
