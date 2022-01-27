package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.MenuEntity;
import com.wms.admin.mapper.MenuMapper;
import com.wms.admin.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.MenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<MenuVO> queryList() {
        QueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MenuEntity::getDelFlag, "1");
        List<MenuEntity> list = list(queryWrapper);
        return toMenuTree(list);
    }

    @Override
    public boolean addMenu(MenuVO menuVO) {
        String parentId = menuVO.getParentId();
        MenuEntity parentMenu = getById(parentId);
        checkParentMenu(parentMenu);
        checkMenuCode(menuVO.getMenuCode());
        MenuEntity menu = new MenuEntity();
        menu.setId(UUIDUtil.uuid());
        menu.setMenuName(menuVO.getMenuName());
        menu.setMenuCode(menuVO.getMenuCode());
        menu.setLevelPath(parentMenu.getLevelPath() + "/" + menu.getId());
        menu.setLevelNo(parentMenu.getLevelNo() + 1);
        menu.setCreateBy("sys");
        menu.setUpdateBy("sys");
        menu.setSeq(menuVO.getSeq());
        menu.setDelFlag("1");
        menu.setType("1"); //菜单
        menu.setParentId(parentMenu.getParentId());
        menu.setUrl(menuVO.getUrl()); //
        return save(menu);
    }

    private void checkParentMenu(MenuEntity parentMenu) {

    }

    @Override
    public boolean updateMenu(MenuVO menuVO) {
        return false;
    }

    @Override
    public boolean deleteMenu(String id) {
        return removeById(id);
    }

    @Override
    public boolean addTopMenu(MenuVO menuVO) {
        checkMenuCode(menuVO.getMenuCode());
        MenuEntity menu = new MenuEntity();
        menu.setId(UUIDUtil.uuid());
        menu.setMenuName(menuVO.getMenuName());
        menu.setMenuCode(menuVO.getMenuCode());
        menu.setLevelPath("/" + menu.getId());
        menu.setLevelNo(1);
        menu.setCreateBy("sys");
        menu.setUpdateBy("sys");
        menu.setSeq(menuVO.getSeq());
        menu.setDelFlag("1");
        menu.setType("1"); //菜单
        menu.setParentId("-1"); //-1表示顶级菜单
        menu.setUrl(menuVO.getUrl()); //
        save(menu);
        return true;
    }

    /**
     * check menuCode unique
     *
     * @param menuCode
     */
    private void checkMenuCode(String menuCode) {


    }

    private List<MenuVO> toMenuTree(List<MenuEntity> list) {
        List<MenuVO> menuVOList = new ArrayList<>();
        list.forEach(item -> {
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(item, menuVO);
            menuVOList.add(menuVO);
        });

        return menuVOList;
    }
}
