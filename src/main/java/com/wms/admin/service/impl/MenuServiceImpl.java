package com.wms.admin.service.impl;

import com.wms.admin.entity.MenuEntity;
import com.wms.admin.mapper.MenuMapper;
import com.wms.admin.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
