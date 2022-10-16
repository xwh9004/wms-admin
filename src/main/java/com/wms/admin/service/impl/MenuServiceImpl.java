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
import com.wms.admin.service.IRoleMenuService;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.MenuVO;
import com.wms.admin.vo.RouteVO;
import com.wms.admin.vo.UserRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-01-19 16:02:56
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements IMenuService {

    private static final Integer TOP_LEVEL = Integer.valueOf(1);
    private static final String TOP_PARENT = "-1";
    private static final String SLASH = "/";

    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 菜单列表
     *
     * @return
     */
    @Override
    public List<MenuVO> queryList() {
        final List<UserRoleVO> roles = UserInfoContext.getUserInfo().getRoles();
        List<String> roleIds = roles.stream().map(UserRoleVO::getRoleId).collect(Collectors.toList());
        List<MenuEntity> list = baseMapper.userMenus(roleIds.get(0));
        return toMenuTree(list);
    }

    /**
     * 用户权限配置使用
     *
     * @return
     */
    @Override
    public List<RouteVO> queryRoutes() {
        QueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(MenuEntity::getDelFlag, WMSConstants.DEL_FLG_N)
                .in(MenuEntity::getType, WMSConstants.MENU_TYPE_DIR, WMSConstants.MENU_TYPE_MENU);
        List<MenuEntity> list = list(queryWrapper);
        List menuList = toMenuTree(list);
        List<RouteVO> routes = toRoutes(menuList);
        return routes;

    }

    /**
     * 用户路由
     *
     * @param roleIds
     * @return
     */
    @Override
    public List<RouteVO> queryRoleRoutes(List<String> roleIds) {
        List<MenuEntity> list = baseMapper.roleMenus(roleIds.get(0));
        List menuList = toMenuTree(list);
        List<RouteVO> routes = toRoutes(menuList);
        return routes;
    }

    private List<RouteVO> toRoutes(List<MenuVO> menuList) {
        if (menuList.isEmpty()) {
            return null;
        }
        List<RouteVO> routes = new ArrayList<>();
        for (MenuVO menu : menuList) {
            RouteVO routeVO = menu.toRoute();
            List<MenuVO> menuChildren = new ArrayList(menu.getChildren());
            if (!menuChildren.isEmpty()) {
                List<RouteVO> children = toRoutes(menuChildren);
                routeVO.setChildren(children);
            }
            routes.add(routeVO);
        }
        return routes;
    }

    @Override
    public boolean addMenu(MenuVO menuVO) {
        MenuEntity parentMenu = getById(menuVO.getParentId());
        if (parentMenu == null) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "父菜单" + menuVO.getParentId());
        }
        checkMenuForAdd(parentMenu, menuVO);
        MenuEntity menu = new MenuEntity();
        BeanUtils.copyProperties(menuVO, menu);
        menu.setId(UUIDUtil.uuid());
        menu.setLevelPath(parentMenu.getLevelPath() + SLASH + menu.getId());
        menu.setLevelNo(parentMenu.getLevelNo() + 1);
        menu.setDelFlag(WMSConstants.DEL_FLG_N);
        menu.setParentId(parentMenu.getId());
        menu.setCreateBy(UserInfoContext.getUsername());
        menu.setUpdateBy(UserInfoContext.getUsername());
        return save(menu);
    }

    private void checkMenuForAdd(MenuEntity parentMenu, MenuVO menuVO) {
        checkParentMenu(parentMenu);
        checkMenuCode(menuVO.getId(), menuVO.getMenuCode());
        Integer parentLevel = parentMenu.getLevelNo();
        checkMenuSeq(parentMenu.getId(), null, Integer.sum(parentLevel, 1), menuVO.getSeq());
    }

    private void checkMenuSeq(String parentId, String menuId, int level, int seq) {
        LambdaQueryWrapper<MenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(MenuEntity::getDelFlag, WMSConstants.DEL_FLG_N)
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

        if (!WMSConstants.DEL_FLG_N.equals(parentMenu.getDelFlag())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, parentMenu.getMenuCode());
        }
        if (!WMSConstants.STATUS_1.equals(parentMenu.getStatus())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_AVAILABLE, parentMenu.getMenuCode());
        }
        if (WMSConstants.MENU_TYPE_RESOURCE.equals(parentMenu.getType())) {
            throw new BusinessException(ResultCode.COMMON_ERROR, "按钮不能添加子菜单");
        }
    }

    @Transactional
    @Override
    public boolean updateMenu(MenuVO menuVO) {
        checkMenuForUpdate(menuVO);
        MenuEntity menuEntity = new MenuEntity();
        BeanUtils.copyProperties(menuVO, menuEntity);
        menuEntity.setUpdateBy(UserInfoContext.getUsername());
        return updateById(menuEntity);
    }

    private void checkMenuForUpdate(MenuVO menuVO) {
        String menuId = menuVO.getId();
        if (StringUtils.isBlank(menuId)) {
            throw new BusinessException(ResultCode.PARAM_NOT_NULL, "菜单ID");
        }
        MenuEntity oldMenu = getById(menuId);
        if (!WMSConstants.DEL_FLG_N.equals(oldMenu.getDelFlag())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "菜单");
        }
        if (!oldMenu.getParentId().equals(menuVO.getParentId())) {
            throw new BusinessException(ResultCode.DATA_NO_MODIFY, "父级菜单");
        }
        checkMenuCode(menuVO.getId(), menuVO.getMenuCode());
        checkMenuSeq(menuVO.getParentId(), menuVO.getId(), menuVO.getLevelNo(), menuVO.getSeq());

        oldMenu.setSeq(menuVO.getSeq());
        oldMenu.setMenuName(menuVO.getMenuName());
        oldMenu.setMenuCode(menuVO.getMenuCode());
    }

    @Override
    public boolean deleteMenu(String id) {
        Assert.notNull(id, "ID不能为空");
        //因为 菜单数据不允许非超级管理操作,可以采用物理删除
        return removeById(id);
    }

    @Override
    public boolean addTopMenu(MenuVO menuVO) {
        checkMenuCode(menuVO.getId(), menuVO.getMenuCode());
        checkMenuSeq("-1", null, 1, menuVO.getSeq());
        MenuEntity menu = new MenuEntity();
        menu.setId(UUIDUtil.uuid());
        menu.setMenuName(menuVO.getMenuName());
        menu.setMenuCode(menuVO.getMenuCode());
        menu.setLevelPath(SLASH + menu.getId());
        menu.setLevelNo(1);
        menu.setCreateBy(UserInfoContext.getUsername());
        menu.setUpdateBy(UserInfoContext.getUsername());
        menu.setSeq(menuVO.getSeq());
        menu.setType(menuVO.getType());
        menu.setStatus(menuVO.getStatus());
        menu.setHidden(menuVO.getHidden());
        menu.setParentId(TOP_PARENT); //-1表示顶级菜单
        menu.setUrl(menuVO.getUrl()); //
        menu.setRedirect(menuVO.getRedirect());
        menu.setPath(menuVO.getPath());
        menu.setIcon(menuVO.getIcon());
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
                .eq(MenuEntity::getDelFlag, WMSConstants.DEL_FLG_N)
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
                if (menuMap.containsKey(parentId)) {
                    MenuVO parentMenu = menuMap.get(parentId);
                    parentMenu.getChildren().add(menu);
                }

            }
        });
        return new ArrayList<>(topMenus);
    }

    private MenuVO toMenuVO(MenuEntity entity) {
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(entity, menuVO);
        return menuVO;
    }

    private void checkMenuUrl(MenuVO menuVO) {

    }
}
