package com.wms.admin.service;

import com.wms.admin.entity.Menu;
import com.wms.admin.mapper.MenuMapper;
import com.wms.admin.util.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class MenuServiceTest {

    @Autowired
    MenuMapper menuMapper;

    @Test
    public void insertMenu(){
        Menu menu = new Menu();
        menu.setId(UUIDUtil.uuid());
        menu.setMenuName("全景视图");
        menu.setMenuCode("dashboard");
        menu.setLevelPath("/"+menu.getId());
        menu.setLevelNo(1);
        menu.setCreateBy("admin");
        menu.setUpdateBy("admin");
        menu.setSeq(1);
        menu.setDelFlag("1");
        menu.setType("1");
        menu.setParentId("-1"); //-1表示顶级菜单
        menu.setUrl("/wms-admin/dashboard"); //
        menuMapper.insert(menu);

    }
}
