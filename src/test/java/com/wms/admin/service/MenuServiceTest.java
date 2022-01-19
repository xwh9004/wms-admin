package com.wms.admin.service;

import com.wms.admin.entity.MenuEntity;
import com.wms.admin.mapper.MenuMapper;
import com.wms.admin.util.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuServiceTest {

    @Autowired
    MenuMapper menuMapper;

    @Test
    public void insertMenu(){
        String name ="全景视图";
        String code ="dashboard";
        int level =1;
        int seq =1;
        String url ="/wms-admin/dashboard";
        MenuEntity menu = createMenu(name,code,level,seq,url);
        menuMapper.insert(menu);
    }

    public MenuEntity createMenu(String name,String code,int level,int seq,String url){
        MenuEntity menu = new MenuEntity();
        menu.setId(UUIDUtil.uuid());
        menu.setMenuName(name);
        menu.setMenuCode(code);
        menu.setLevelPath("/"+menu.getId());
        menu.setLevelNo(level);
        menu.setCreateBy("admin");
        menu.setUpdateBy("admin");
        menu.setSeq(seq);
        menu.setDelFlag("1");
        menu.setType("1");
        menu.setParentId("-1"); //-1表示顶级菜单
        menu.setUrl(url); //
        return menu;
    }
}
