package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.MenuEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.MenuMapper;
import com.wms.admin.service.IMenuService;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.MenuVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-01-19 16:02:56
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements IMenuService {

    private static final Integer TOP_LEVEL = Integer.valueOf(1);
    private static final String TOP_PARENT = "-1";

    @Override
    public List<MenuVO> queryList() {
        QueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MenuEntity::getDelFlag, WMSConstants.DEL_FLG_1);
        List<MenuEntity> list = list(queryWrapper);
        return toMenuTree(list);
    }

    @Override
    public boolean addMenu(MenuVO menuVO) {
        String parentId = menuVO.getParentId();
        MenuEntity parentMenu = getById(parentId);
        checkParentMenu(parentMenu);
        checkMenuCode(menuVO.getId(), menuVO.getMenuCode());
        checkMenuForAdd(parentMenu, menuVO);
        MenuEntity menu = new MenuEntity();
        menu.setId(UUIDUtil.uuid());
        menu.setMenuName(menuVO.getMenuName());
        menu.setMenuCode(menuVO.getMenuCode());
        menu.setLevelPath(parentMenu.getLevelPath() + "/" + menu.getLevelPath());
        menu.setLevelNo(parentMenu.getLevelNo() + 1);
        menu.setCreateBy(UserInfoContext.getUsername());
        menu.setUpdateBy(UserInfoContext.getUsername());
        menu.setSeq(menuVO.getSeq());
        menu.setDelFlag(WMSConstants.DEL_FLG_1);
        menu.setType(WMSConstants.RESOURCE_TYPE_MENU); //菜单
        menu.setParentId(parentMenu.getId());
        menu.setUrl(menuVO.getUrl()); //
        return save(menu);
    }

    private void checkMenuForAdd(MenuEntity parentMenu, MenuVO menuVO) {
        Integer parentLevel = parentMenu.getLevelNo();
        checkMenuSeq(parentMenu.getId(),null, Integer.sum(parentLevel, 1), menuVO.getSeq());
    }

    private void checkMenuSeq(String parentId,String menuId, int level, int seq) {
        LambdaQueryWrapper<MenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(MenuEntity::getDelFlag, WMSConstants.DEL_FLG_1)
                .eq(MenuEntity::getLevelNo, level)
                .eq(MenuEntity::getParentId, parentId)
                .eq(MenuEntity::getSeq, seq);
        if (StringUtils.isNotBlank(menuId)) {
            queryWrapper.ne(MenuEntity::getId, menuId);
        }
        if (this.baseMapper.selectCount(queryWrapper) > 0) {
            throw new BusinessException(ResultCode.DATA_REPEAT, "seq " + seq);
        }
    }

    /**
     * 添加次级菜单，需要校验父级菜单有效性，类型是否是菜单
     *
     * @param parentMenu
     */
    private void checkParentMenu(MenuEntity parentMenu) {
        if (!WMSConstants.DEL_FLG_1.equals(parentMenu.getDelFlag())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, parentMenu.getMenuCode());
        }
        if (!WMSConstants.STATUS_1.equals(parentMenu.getStatus())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_AVAILABLE, parentMenu.getMenuCode());
        }
        if (WMSConstants.RESOURCE_TYPE_BTN.equals(parentMenu.getType())) {
            throw new BusinessException(ResultCode.COMMON_ERROR, "按钮不能添加子菜单");
        }
    }

    @Transactional
    @Override
    public boolean updateMenu(MenuVO menuVO) {
        checkMenuForUpdate(menuVO);
        MenuEntity menuEntity = new MenuEntity();
        BeanUtils.copyProperties(menuVO, menuEntity);
        return updateById(menuEntity);
    }

    private void checkMenuForUpdate(MenuVO menuVO) {
        String menuId = menuVO.getId();
        if (StringUtils.isBlank(menuId)) {
            throw new BusinessException(ResultCode.PARAM_NOT_NULL, "菜单ID");
        }
        MenuEntity oldMenu = getById(menuId);
        if (!WMSConstants.DEL_FLG_1.equals(oldMenu.getDelFlag())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "菜单");
        }
        if (!oldMenu.getParentId().equals(menuVO.getParentId())) {
            throw new BusinessException(ResultCode.DATA_NO_MODIFY, "父级菜单");
        }
        checkMenuCode(menuVO.getId(), menuVO.getMenuCode());
        checkMenuSeq(menuVO.getParentId(),menuVO.getId(), menuVO.getLevelNo(), menuVO.getSeq());

        oldMenu.setSeq(menuVO.getSeq());
        oldMenu.setMenuName(menuVO.getMenuName());
        oldMenu.setMenuCode(menuVO.getMenuCode());
    }

    @Override
    public boolean deleteMenu(String id) {
        return removeById(id);
    }

    @Override
    public boolean addTopMenu(MenuVO menuVO) {
        checkMenuCode(menuVO.getId(), menuVO.getMenuCode());
        checkMenuSeq("-1",null,1,menuVO.getSeq());
        MenuEntity menu = new MenuEntity();
        menu.setId(UUIDUtil.uuid());
        menu.setMenuName(menuVO.getMenuName());
        menu.setMenuCode(menuVO.getMenuCode());
        menu.setLevelPath("/" + menu.getId());
        menu.setLevelNo(1);
        menu.setCreateBy(UserInfoContext.getUsername());
        menu.setUpdateBy(UserInfoContext.getUsername());
        menu.setSeq(menuVO.getSeq());
        menu.setType(WMSConstants.RESOURCE_TYPE_MENU); //菜单
        menu.setParentId("-1"); //-1表示顶级菜单
        menu.setUrl(menuVO.getUrl()); //
        return save(menu);
    }

    /**
     * check menuCode unique
     *
     * @param menuCode
     */
    private void checkMenuCode(String menuId, String menuCode) {

        LambdaQueryWrapper<MenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(MenuEntity::getDelFlag, WMSConstants.DEL_FLG_1)
                .eq(MenuEntity::getMenuCode, menuCode);
        if (StringUtils.isNotBlank(menuId)) {
            queryWrapper.ne(MenuEntity::getId, menuId);
        }
        if (this.baseMapper.selectCount(queryWrapper) > 0) {
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "菜单编号" + menuCode);
        }
    }

    private List<MenuVO> toMenuTree(List<MenuEntity> list) {
        Map<String, MenuVO> menuMap = new HashMap<>();
        list.stream().forEach(item -> menuMap.put(item.getId(), toMenuVO(item)));
        Set<MenuVO> topMenus = new TreeSet<>();
        menuMap.forEach((id, menu) -> {
            String parentId = menu.getParentId();
            if (TOP_PARENT.equals(parentId)) {
                topMenus.add(menu);
            } else {
                MenuVO parentMenu = menuMap.get(parentId);
                parentMenu.getChildren().add(menu);
            }
        });
        return new ArrayList<>(topMenus);
    }

    private MenuVO toMenuVO(MenuEntity entity) {
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(entity, menuVO);
        return menuVO;
    }

    private void checkMenuUrl(MenuVO menuVO){

    }
}
